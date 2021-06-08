# Arcana
_© 2021 Michael Frank_

Welcome to **Arcana**! 


I created this game just for fun, as a summer project, as well as something to add to my resume.
As such, don't expect anything super complicated - it's more of a proof of concept. 

[Development Info](#Development)

[How to Run](#How To Run)

[How to Play](#How To Play)

**Arcana is currently in Alpha, and is not available for download at this time.**

- Tentative date for **BETA** release: **July 1, 2021**

- Tentative date for **FULL** release: **August 1, 2021**

Note that these release dates are tentative and are subject to change should I run into too many issues.

# Development
### Source Code
The source code for this project can be located in `src/dev/mfrank`

### Purpose
This project was programmed entirely in Java, using no framework whatsoever. 
The purpose of writing this project from scratch was mainly to challenge myself, 
as the game's main intention was to be a Java learning experience. 
Of course, being a game, making it fun was also the second largest priority.

### Paladin
In order to make Arcana more accessible and fun to play, I created an I/O engine called **Paladin** that allows the player to control what their character does through simple commands. Paladin reads these simple one-word commands, then determines how to interpret them based off of context. In order to make something so simple for the player, I had to make it more complicated behind the scenes. In fact, Paladin is what comprises most of the game's code - very little is actually related to gameplay. Because most of the game's code contributes to Paladin in some way, I gave it its own info section in this document. 


# How To Run
### 1. Download Java

Because Arcana was made in Java, it requires a **JRE** (Java Runtime Environment) 
to run properly. The latest version of Java can be downloaded here: 

https://java.com/en/download/windows_manual.jsp

### 2. Download game 
**Arcana is not currently available to play.** When I deem the game suitable for public testing, I will release a beta version in the `releases` tab of this GitHub page. In the same way, when I deem the game to be finished, I will release the full version in the `releases` section. If you want more information on tentative release dates, see the **Arcana** section at the top of this document.

### 3. Run game
The game can simply be run by running the Arcana.exe file. 

Upon running for the first time, it will create a folder under `C:\Users\[current user]\Documents\My Games\` called `Arcana`. 

**Do not delete this folder!**
All of your save files will be stored inside it.

Every subsequent time the game is run, it will ask you if you'd like to create a new save or load an existing one.
In the former case, a new `.dat` file will be created in the aforementioned save directory.
In the latter case, the specified `.dat` file will be loaded from the save directory.

_Please do not attempt to modify save files unless you seriously know what you're doing --
you could potentially break the game, and be forced to re-install everything._

# How To Play
Arcana is a spellcasting game with a linear story. This means that it's not an RPG - you don't
have choices in where to go or what to do, you only have to work your way through waves of enemies.
Making the game an RPG was considered at one point, but was quickly cast aside due to time restraints.

Since Arcana is a text-based game, all forms of input and output are run through the console.
More information will be available here soon.
