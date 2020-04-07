package monopoly;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gina Salib
 */
public class BuildEvenException extends Exception{
    @Override
    public String getMessage()
    {
        return "You must build evenly";
    }
    
}
