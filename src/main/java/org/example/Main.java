package org.example;

import Controlleur.ContAccueil;
import logs.FormatterLog;
import logs.MyLogger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = MyLogger.LOGGER;// Logger instance
    private static FileHandler fh = null;

    public static void main(String[] args) throws Exception {

            try {
                //Lancement du fichier log
                fh = new FileHandler("ReversoLog.log", true);
                MyLogger.LOGGER.setUseParentHandlers(false);
                MyLogger.LOGGER.addHandler(fh);
                fh.setFormatter(new FormatterLog());
                //log debut
                MyLogger.LOGGER.log(Level.INFO, "debut d'application");

                //lancement du programme
                ContAccueil.initAccueil();
                System.out.println("ReversoLog");

                //Log fin
                MyLogger.LOGGER.log(Level.INFO, "fin d'application");
            }catch (IOException | SecurityException  fe) {
                MyLogger.LOGGER.log(Level.SEVERE, "message d'erreur fichier  "+ fe.getMessage() + " " + fe);
            }
            catch (Exception e ) {
                MyLogger.LOGGER.log(Level.SEVERE, "message de la classe Exception  "+ e.getMessage() + " " + e);
                System.exit(1);
            }
        }
    }
