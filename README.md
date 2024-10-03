# Firebase Authentication with Firebase Emulator Suite

This simple project demonstrates how to integrate Firebase Authentication into an Android app and configure it to use the Firebase Emulator Suite for local development and testing.

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Project Setup](#project-setup)
4. [Configure Firebase Emulator Suite](#configure-firebase-emulator-suite)
5. [Authentication Setup](#authentication-setup)
6. [Testing Authentication with Emulator](#testing-authentication-with-emulator)
7. [Running the Project](#running-the-project)

## Overview
This project showcases how to set up Firebase Authentication in an Android project using the Firebase Emulator Suite for local testing. The Emulator Suite allows testing Firebase functionalities locally without interacting with live production data.

## Prerequisites
Before setting up Firebase Authentication, make sure you have the following installed:
- [Android Studio](https://developer.android.com/studio)
- [Node.js](https://nodejs.org/) (for running Firebase Emulator)
- [Firebase CLI](https://firebase.google.com/docs/cli) (v9.0.0 or later)
- A Google Firebase project

## Project Setup

1. **Add Firebase SDK to Android Project**  
   - Open your project-level `build.gradle` file and ensure you have the following classpath for Firebase:

     ```groovy
     dependencies {
         classpath 'com.google.gms:google-services:4.3.15'
     }
     ```

   - In your app-level `build.gradle` file, add Firebase Authentication dependency:

     ```groovy
     dependencies {
         implementation 'com.google.firebase:firebase-auth:22.1.1'
     }
     ```

   - Apply the Firebase plugin at the bottom of the `build.gradle` file:

     ```groovy
     apply plugin: 'com.google.gms.google-services'
     ```

2. **Add Firebase Configuration File**
   - Download the `google-services.json` file from the Firebase Console.
   - Place the file in your project’s `app` directory.

3. **Firebase CLI Setup**
   - Install Firebase CLI by running the following command:

     ```bash
     npm install -g firebase-tools
     ```

   - Log in to Firebase:

     ```bash
     firebase login
     ```

## Configure Firebase Emulator Suite

1. **Initialize Firebase Emulator**
   - Open your terminal in the root directory of the project and run:

     ```bash
     firebase init emulators
     ```

   - Select `Authentication` and configure the port (default is `9099`).

2. **Emulator Configuration**
   - After initialization, modify the `firebase.json` configuration file to add the authentication emulator:

     ```json
     {
       "emulators": {
         "auth": {
           "port": 9099
         }
       }
     }
     ```

3. **Start Firebase Emulator**
   - Start the Firebase Emulator Suite by running:

     ```bash
     firebase emulators:start
     ```

4. **Connect Firebase to Emulator in Android**
   - In your Android project, configure Firebase Authentication to use the emulator by adding the following code to your initialization logic:

     ```kotlin
     val firebaseAuth = FirebaseAuth.getInstance()
     if (BuildConfig.DEBUG) {
         firebaseAuth.useEmulator("10.0.2.2", 9099) // For Android Emulator
     }
     ```

     **Note:** Use `10.0.2.2` to access the host machine's localhost when running the Android app in the emulator.

## Authentication Setup

1. **Sign-In Methods**
   - Go to your Firebase Console, navigate to **Authentication** → **Sign-In Methods**.
   - Enable the desired sign-in methods (e.g., Email/Password, Google, etc.).

2. **Sample Authentication Code**
   - Here is an example of how to authenticate a user using email and password:

     ```kotlin
     val email = "user@example.com"
     val password = "yourpassword"

     firebaseAuth.signInWithEmailAndPassword(email, password)
         .addOnCompleteListener { task ->
             if (task.isSuccessful) {
                 // Authentication succeeded
                 val user = firebaseAuth.currentUser
                 println("User signed in: ${user?.email}")
             } else {
                 // Authentication failed
                 println("Sign-in failed: ${task.exception?.message}")
             }
         }
     ```

## Testing Authentication with Emulator

1. **Using the Emulator**
   - When running your Android app, ensure the Firebase Emulator is active by starting it with the command:

     ```bash
     firebase emulators:start
     ```

2. **Test Sign-In and Sign-Up**
   - Use your app to perform sign-in or sign-up with the email/password flow. The data will be stored in the Emulator Suite and not in the actual Firebase backend.

3. **View Emulator Suite Dashboard**
   - You can view active authentication sessions and manage users from the Firebase Emulator UI at:

     ```bash
     http://localhost:4000
     ```
   - Below is a screenshot of the Firebase Emulator Suite:

     ![image](https://github.com/user-attachments/assets/107f71a6-05d0-407a-8182-39760ff78668)


## Running the Project

To run the project with Firebase Authentication connected to the Emulator Suite:

1. Ensure the Firebase Emulator Suite is running.
2. Launch your Android project from Android Studio.
3. Sign in with a test account or create a new one. Test accounts will be stored locally in the emulator environment.
