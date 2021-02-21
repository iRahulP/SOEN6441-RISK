package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Order {

    private int d_numArmies;
    private String d_countryId;
    public AssignReinforcement d_Arc;
    private Player d_player;
    /**
     * This constructor will initialize the order object with deploy details.
     * @param p_player current player issuing deploy order
     * @param p_countryId country where armies will be deployed
     * @param p_numArmies total armies which will be deployed
     */
    public Order(Player p_player,String p_countryId,int p_numArmies) {
        d_player = p_player;
        d_countryId = p_countryId;
        d_numArmies = p_numArmies;
    }

    /**
     * method which enacts the order
     * @return true if successful, else false
     */
    public boolean execute(Order p_order){

        d_player = p_order.getD_player();
        d_countryId = p_order.getD_countryId();
        d_numArmies = p_order.getD_numArmies();


        CountryDetails c= d_player.getOwnedCountries().get(d_countryId.toLowerCase());
        int existingArmies = c.getNumberOfArmies();
        existingArmies += d_numArmies;
        c.setNumberOfArmies(existingArmies);

        return true;
    }

    /**
     * Getter for current player
     * @return d_player
     */
    public Player getD_player() {
        return d_player;
    }

    /**
     * Setter for current player
     * @param d_player player
     */
    public void setD_player(Player d_player) {
        this.d_player = d_player;
    }

    /**
     * Getter for ID of Country
     * @return d_countryId
     */
    public String getD_countryId() {
        return d_countryId;
    }

    /**
     * Setter for ID of Country
     * @param d_countryId country ID
     */
    public void setD_countryId(String d_countryId) {
        this.d_countryId = d_countryId;
    }

    /**
     * Setter for number of armies
     * @return d_numArmies
     */
    public int getD_numArmies() {
        return d_numArmies;
    }

    /**
     * Setter for number of armies
     * @param d_numArmies number of armies
     */
    public void setD_numArmies(int d_numArmies) {
        this.d_numArmies = d_numArmies;
    }

}
