/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.util.concurrency;

/**
 *
 * @author Sergio
 */
public class Paralell {

    /**
     * paraleliza uma funcoa
     *
     * @param run
     */
    public static void start(Runnable run) {
        new Thread(run).start();
    }
}
