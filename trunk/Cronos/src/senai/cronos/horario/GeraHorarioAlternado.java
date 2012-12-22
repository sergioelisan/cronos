package senai.cronos.horario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;
import senai.util.date.DateUtil;

public class GeraHorarioAlternado extends GeraHorario {

    @Override
    public void alocarAulas(Map<Date, Tupla<Aula, Aula>> horario) throws ClassNotFoundException, SQLException {
        Set<Date> keySet = null;
       for(int modulo=1;modulo<3;modulo++){
           if(keySet==null){
              keySet=horario.keySet();
           
             
           }
           for (Date dia : keySet) 
                    System.out.println("modulo:"+modulo+"//dia:"+dia.toString());
           int modo = 0;      
           TreeSet ts=new TreeSet();
           ts.addAll(keySet);
           if(ts.first().toString().contains("fri")||ts.first().toString().contains("wed")||ts.first().toString().contains("mon")){
                        modo=0;
                    }else{
                        modo=1;
                    }
       
        for (UnidadeCurricular uc : getDisciplinas(modulo)) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_INTEIRO);

            for (int i = 0; i < total; i++) {
                OUTER:
                for (Date dia : keySet) {
                   
                    String diaSemana = DateUtil.getNomeDia(dia);
                    if (modo == 0) {
                        switch (diaSemana) {
                            case DateUtil.SEG:
                            case DateUtil.QUA:
                            case DateUtil.SEX:
                                if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                                    horario.get(dia).setPrimeiro(aula);
                                    horario.get(dia).setSegundo(aula);

                                    break OUTER;
                                }
                                break;
                        }
                    } else {
                        switch (diaSemana) {
                            case DateUtil.TER:
                            case DateUtil.QUI:
                                if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                                    horario.get(dia).setPrimeiro(aula);
                                    horario.get(dia).setSegundo(aula);

                                    break OUTER;
                                }
                                break;
                        }
                    }
                }
            }

            modo = (modo == 0) ? 1 : 0;
        }
        if(modulo<3) keySet=limparHorario(horario,modulo);
       }
    }

    public Set<Date> limparHorario(Map<Date, Tupla<Aula, Aula>> horario, int t) {
        Set<Date> set=new LinkedHashSet();
        Date[] datas = new Date[500];
        Map<Date, Tupla<Aula, Aula>> temp = new TreeMap<>();
        temp=Horario.create();
        temp.keySet().toArray(datas);
        int cont=0;
        for (int j = 0; j <= temp.size()-1; j++) {
             if(cont>2) break;
             for (int i = 0; i <= horario.size()-1; i++) {
                 if (horario.get(datas[i]).getPrimeiro().equals(Aula.PADRAO)
                        && horario.get(datas[i]).getSegundo().equals(Aula.PADRAO)) {
                     cont++;
                 } else {
                    if(j>=temp.size()) break;
                    temp.put(datas[j], horario.get(datas[i]));
                    j++;
                    cont=0;
                 }
             }
        }
       horario.clear();
       horario.putAll(temp);
     
       for (Date dia2 : horario.keySet()) {
           
           if (horario.get(dia2).getPrimeiro().equals(Aula.PADRAO)) {
              // System.out.println(dia2.toString()); 
               set.add(dia2);
            }
        }
       
    
        return set;
    }
    private Docente lastDocente = Docente.PADRAO;

    @Override
    public void alocarDocentes(Map<Date, Tupla<Aula, Aula>> horario) throws Exception {
        Horario wrapper = new Horario(horario);

        for (Aula aula : wrapper.getAulas()) {
            Map<Date, Tupla<Boolean, Boolean>> dias = wrapper.getDiasLecionados(aula);

            for (Docente docente : CronosAPI.bestDocentes(aula.getDisciplina())) {

                boolean disponivel = true;

                for (Date dia : dias.keySet()) {
                    Integer metade = dias.get(dia).getPrimeiro() ? Tupla.PRIMEIRA : Tupla.SEGUNDA;

                    if (!docente.getHorarioDocente().isDisponivel(dia, getTurma().getTurno(), metade)) {
                        disponivel = false;
                        break;
                    }
                }

                if ((disponivel && !lastDocente.equals(docente))
                        && getTurma().getTurno().isInside(docente.getTurno())) {
                    aula.setDocente(docente);
                    lastDocente = docente;
                    break;
                }

            }

        }

    }
}
