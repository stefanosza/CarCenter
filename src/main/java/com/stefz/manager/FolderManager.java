/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefz.manager;


import com.stefz.Session.Settings;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author stefz
 */
public class FolderManager {
    
    String userDirectory = Settings.USER_PATH;
    Path ccDirectory = Paths.get ( userDirectory, "CarCanter" );
    Path configDirectory = ccDirectory.resolve ( "config" );

    public FolderManager () {
    }

    public void createCoreFilesAndDirectories () {
        try {
            //Create core directories
            if ( !Files.exists ( ccDirectory ) ) {

                Files.createDirectories ( configDirectory );

                System.out.println ( "CarCanter directories created successfully" );
            } else {
                System.out.println ( "CarCanter directory already exists" );
            }

            Path ccDBFile = configDirectory.resolve ( "cc.db" );
            if ( !Files.exists ( ccDBFile ) ) {
                Files.createFile ( ccDBFile );

                System.out.println ( "cc.db file created in config directory" );
            } else {
                System.out.println ( "cc.db file already exists" );
            }

        } catch (Exception e) {
            System.out.println ( "An error occurred: " + e.getMessage () );
            e.printStackTrace ();
        }
    }
}
