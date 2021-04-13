package model;

import java.util.Random;

/**
 * AggresivePlayerStrategy class is ConcreteStrategy.
 * This strategy focuses on centralization of forces and then attacks.
 * @author  Aarthi
 */
public class AggresivePlayer extends PlayerStrategy{

    private int d_OrderVal;
    CountryDetails d_StrongestCountry,d_DefendingCountry;
    /**
     * default Constructor for PlayerStrategy
     *
     * @param p_player player object
     * @param p_map    map object
     */
    public AggresivePlayer(Player p_player, GameMap p_map) {
        super(p_player, p_map);
        d_StrongestCountry = null;
        d_DefendingCountry = null;
    }


    /**
     * Finds country with highest number of armies owned by player
     * @return strongest CountryDetails object
     */
    private void findStrongestCountryDetails()
    {
        int l_maxArmies = 0, l_numArmies;
        for(CountryDetails l_countryDetails : this.d_Player.getOwnedCountries().values()) {
            l_numArmies = l_countryDetails.getNumberOfArmies();
            if( l_numArmies > l_maxArmies) {
                l_maxArmies = l_numArmies;
                d_StrongestCountry = l_countryDetails;
            }
        }
    }

    /**
     *toAttack function implements attacks to the
     * neighboring country from strongest country
     * @return country to attack
     */
    protected CountryDetails toAttack()
    {
        for(CountryDetails l_neighborCountry : d_StrongestCountry.getNeighbours().values())
        {
           if(!this.d_Player.getOwnedCountries().containsKey(l_neighborCountry))
           {
                d_DefendingCountry = l_neighborCountry;
                return  d_DefendingCountry;
           }
        }

        //if no neighbors found to attack
        return null;
    }

    /**
     * This function finds the strongest country from where the attack
     * takes place.
     * @return country to attack from
     */
    protected CountryDetails toAttackFrom()
    {
        findStrongestCountryDetails();
        return d_StrongestCountry;
    }

    protected CountryDetails toMoveFrom()
    {
        //TODO
        return d_StrongestCountry;
    }

    /**
     *  This function  sets  the randomOrderValue which can be 0,1,2
     *  These values 0,1,2 will be used for creating orders
     */
    public void setRandomOrderValue(int p_randomValue)
    {
        d_OrderVal = p_randomValue;
    }

    @Override
    public Order createOrder() {

        CountryDetails l_attackingCountry,l_defendingCountry;
        l_attackingCountry = toAttackFrom();
        l_defendingCountry = toAttack();
        Random l_random = new Random();

        switch(d_OrderVal) {
            case 0:
            if (l_random.nextInt(5) != 0) {
                return new Deploy(d_Player, d_StrongestCountry.getCountryId(), l_random.nextInt(20));
            }
            break;

            case 1:
            //create attack Order
            if(l_defendingCountry!=null)
              return new Advance(d_Player, l_attackingCountry.getCountryId(), l_defendingCountry.getCountryId(), l_random.nextInt(6), d_DefendingCountry.getOwnerPlayer());
            else
                System.out.println("Neighbor does not exist for this country");
            break;

            case 2:
            //move armies
        }
        return null;

    }
}
