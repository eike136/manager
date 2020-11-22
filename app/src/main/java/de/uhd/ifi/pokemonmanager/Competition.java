package de.uhd.ifi.pokemonmanager;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


public class Competition extends Swap {
    private Pokemon sourcePokemon;
    private Pokemon targetPokemon;
    private Trainer sourceTrainer;
    private Trainer targetTrainer;


    public Competition(){}

    @Override
    public void execute(Pokemon sourcePokemon, Pokemon targetPokemon) {

        if (sourcePokemon.getTrainer() != targetPokemon.getTrainer()) {
            System.out.println(sourcePokemon.getName() + " vs. " + targetPokemon.getName()+ ": Fight!");

            // store Pokemons and Trainers in the Competition
            this.sourcePokemon = sourcePokemon;
            this.targetPokemon = targetPokemon;
            this.sourceTrainer = sourcePokemon.getTrainer();
            this.targetTrainer = targetPokemon.getTrainer();

            // remove the Pokemons from the Trainers
            this.sourceTrainer.getPokemons().remove(sourcePokemon);
            this.targetTrainer.getPokemons().remove(targetPokemon);
            // reassign the Pokemons to the Trainers

            int min = 0;
            int max = 9;

            if(sourcePokemon.getValue() * ThreadLocalRandom.current().nextInt(min, max + 1)
                    < targetPokemon.getValue() * ThreadLocalRandom.current().nextInt(min, max + 1)) {
                System.out.println(targetPokemon.getName() + " won!");
                this.targetTrainer.addPokemon(sourcePokemon);
                this.targetTrainer.addPokemon(targetPokemon);
            } else {
                System.out.println(sourcePokemon.getName() + " won!");
                this.sourceTrainer.addPokemon(sourcePokemon);
                this.sourceTrainer.addPokemon(targetPokemon);
            }
            // store the Competition in Pokemons Competition history
            sourcePokemon.addCompetition(this);
            targetPokemon.addCompetition(this);
        } else {
            System.out.println("No competition: Trainers "+ sourcePokemon.getTrainer() + " and " + targetPokemon.getTrainer() + " are identical");
            //System.err.printf("No competition: Trainers '%s' == '%s' are identical!%n", sourcePokemon.getTrainer(),
            //      targetPokemon.getTrainer());
        }
    }


}