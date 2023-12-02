package oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  ***********
 *  * English *
 *  ***********
 * You are a software engineer working for the IPCC ("GIEC" in French). Your task is to implement the database that
 * will collect all the records in temperature around the world. This database will be used by the IPCC to monitor the
 * global warming. Once a new record in temperature is observed, it must be communicated to other institutions (for
 * instance, to create publications in newspapers or to inform the scientific community).
 * <p>
 * Your task is to implement the "IPCCDatabase" class below, according to the Observer/Observable design pattern.
 *
 *  ************
 *  * Français *
 *  ************
 * Vous êtes un ingénieur logiciel travaillant pour le GIEC.
 * Votre tâche est d'implémenter la base de données qui
 * collectera tous les records de température à travers le monde.
 * Cette base de données sera utilisée par le GIEC pour surveiller le
 * réchauffement climatique. Lorsqu'un nouveau record de température est observé,
 * il doit être communiqué à d'autres institutions (par exemple, pour créer des
 * publications dans les journaux ou informer la communauté scientifique).
 * <p>
 * Votre mission est d'implémenter la classe "IPCCDatabase" ci-dessous, selon le design pattern Observer/Observable.
 */
public class GlobalWarming {


    /**
     * Observer class that abstracts the subsequent software systems that will further process the new temperature
     * records (for instance, a newspaper or a scientific institution). You do *not* have to implement those software
     * systems.
     */
    public interface RecordObserver {
        public void signalNewRecord(String place, float temperature);
    }


    /**
     * This class represents the IPCC database. It associates the name of places on Earth with the maximum
     * temperature that has been observed at that place so far.
     */
    static public class IPCCDatabase {

        // Hint 1: You will need to store the past records in some data structure.
        // Hint 2: You will need to store the observers in some data structure.

        /**
         * HashMap is just a Java dictionary
         * record_database a single HashMap with the city (key) and temperature (value)
         * observers an ArrayList that stock the cities as an RecordObserver object
         * observers and city are not the same
         * !! primitive type are not allowed in HashMap so for float we use the envelope class Float
         * when creating a dic it's better to put Map instead of HashMap (its more flexible because its abstract)
         */
        private Map<String, Float> record_database = new HashMap<String, Float>();
        private List<RecordObserver> observers = new ArrayList<>();

        /**
         * This method registers a new institution that is interested in being warned in real time of new temperature
         * records.
         *
         * @param observer The observer to be registered.
         */
        void addObserver(RecordObserver observer) {
            // TODO
            observers.add(observer);
        }

        /**
         * This method is called when a new temperature has been measured
         * by some sensor around the world.
         * If a new record is established for the place, then all the observers must be notified.
         *
         * @param place       The place in the world where the temperature was measured.
         * @param temperature The measured temperature.
         */
        public void temperatureMeasured(String place, float temperature) {
            // TODO
            // checking if its a new record (the place is not yet in the dic or the temperature is higher)
            if (!record_database.containsKey(place) || (record_database.get(place)< temperature)) {
                record_database.put(place, temperature);
                for (RecordObserver observer : observers){
                    observer.signalNewRecord(place, temperature);
                }
            }



        }
    }
}
