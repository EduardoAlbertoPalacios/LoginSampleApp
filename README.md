# Summary

Android application to login user, verifying the user credentials (email and password) in a fake environment web service.If the user authenticates successfully, an alert will be displayed with a success message.

# Language
The entire project is designed with kotlin language.

## Project structure
The project implement the clean architecture guidelines as follows:

## Presentation

Defines the code related to the construction of views and UI logic, this layer will show the data from de domain module (use case).
Use of Jetpack compose, MVVM and State flow in this layer.

## Domain

Use cases are defined in this module and encapsulate the business logic of the aplication, 
for instance complex alghoritms can be designed in this module.
They take input and transform it into output in a way specific to the app.

## Data

Provides the data that will be exposed in the UI Layer after mapping it from the domain layer.
The entry points to the data layer are always the repository and Data source.
The repository acts as a bridge between the domain layer and Data source encapsulate the network client or a instance db local.

# Testing

**Android testing**

Instrumentation test are defined for the UI, simulating user interactions and validate the behavior of the user interface.
LoginScreen, AlertDialog and another components were tested.

**Unit testing** 

These tests focus on testing the individual components and their behavior in isolation.
This project includes unit tests.

## Login Application 
| Happy Path    |  Unhappy Path |
| ------------- | ------------- |
| <video src=https://github.com/EduardoAlbertoPalacios/LoginSampleApp/assets/36978350/8d561c96-bfc2-480f-8621-357bba3d8eaf > |  <video src =https://github.com/EduardoAlbertoPalacios/LoginSampleApp/assets/36978350/d4943474-2544-449c-a06c-d5c218a71711>  |






