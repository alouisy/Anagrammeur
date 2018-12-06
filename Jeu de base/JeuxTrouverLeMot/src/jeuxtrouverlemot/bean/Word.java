/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jeuxtrouverlemot.bean;

/**
 *
 * @author darutydegrandprevincent
 */
public interface Word {

    public abstract void init();
    public abstract String mixedWord();
    public abstract String mixedRandomWord();
    public abstract boolean isEquals(String text);

}
