package de.uhd.ifi.pokemonmanager;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Competition extends Swap{

    public Competition() {
    }

    @Override
    public void execute(Pokemon sourcePokemon, Pokemon targetPokemon) {

        if (sourcePokemon.getTrainer() != targetPokemon.getTrainer()) {

            setSourcePokemon(sourcePokemon);
            setTargetPokemon(targetPokemon);

            setDate(new Date());
            setId("" + System.currentTimeMillis());

            // match
            int min = 0;
            int max = 9;

            if(sourcePokemon.getValue() * ThreadLocalRandom.current().nextInt(min, max + 1)
                    < targetPokemon.getValue() * ThreadLocalRandom.current().nextInt(min, max + 1)){
                sourcePokemon.getTrainer().getPokemons().remove(sourcePokemon);
                targetPokemon.getTrainer().getPokemons().add(sourcePokemon);
                targetPokemon.setTrainer(sourcePokemon.getTrainer());

            }
            else{
                targetPokemon.getTrainer().getPokemons().remove(targetPokemon);
                sourcePokemon.getTrainer().getPokemons().add(targetPokemon);
                sourcePokemon.setTrainer((targetPokemon.getTrainer()));
            }

            // store the Swap in Pokemons Swap history
            sourcePokemon.addCompetions(this);
            targetPokemon.addCompetions(this);
        }

        else {
            System.out.printf("No swap: Trainers '%s' == '%s' are identical!%n", sourcePokemon.getTrainer(),
                    targetPokemon.getTrainer());
        }
    }
}

