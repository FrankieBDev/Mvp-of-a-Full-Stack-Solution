### A College Assignment
### Submitted as part of my L4 Software Development Apprenticeship
### Module Name: Advanced Programming
### Feburary 2024.
---
The assignment criteria was as follows:
Part B - MVP of a full-stack solution

- Create a minimal viable product of a full-stack system.
- There should be a front end, a back end and database.
- This could be an Online Booking System (e.g. for a restaurant), Task Management Application, Weather App,  Transport App (e.g. see next tram times), Weather  Multiplayer network game or anything of your choosing. Your choice could be linked to work at your employer or a personal project.
- Front-end is expected to include user interface input, output, validation, etc.  
- Middle is expected to provide operational logic. 
- Back is expected to include database management capabilities. 
---
**TL;DR**
- An MVP for a full stack android app showing iconic locations relating to LGBT History in Manchester.
- Tech Stack:
  - Kotlin
  - Jetpack Compose
  - Room DB
  - Google Maps SDK
---

### Youtube Demo Link
[https://youtu.be/xd-EbStBFMA](https://www.youtube.com/watch?v=SyspjHSya3w)

### Description

MVP for an informative LGBT History of Manchester Android Application.  The app includes informative descriptions of iconic landmarks and locations relating to LGBT history and historic events in Manchester.  A database holds pre-populated information of six locations locally, as well as accepting new additions by the user.  There is also a map view of the displayed locations.


### Story

**As a** mobile app user

**I want to** see existing locations on the home screen

**So that** I can browse info in a convenient way 

**And** interact with only specific locations.
<br/> <br/>

**As a** mobile app user

**I want to** add and remove locations

**So that** I can customise the app to my needs
<br/> <br/>

**As a** mobile app user

**I want to** have access to a map view

**So that** I can view locations on the map in relation to one another
<br/> <br/>

**As a** mobile app user

**I want to** have easy access to a navigation system

**So that** I can switch between screens easily



### Acceptance Criteria

**Prerequisite:**

**Given** I am on the Home Screen

**When** A location button is tapped.

**Then** I see an info pop up

<br/> <br/>
**Given** I am on the Home Screen

**When** I tap a navigation button

**Then** I am taken to the corresponding screen

<br/> <br/>
**Given** I am on the List Screen

**When** I tap the "+" button

**Then** I am taken to the "Add Location Screen"

<br/> <br/>
**Given** I am on the List Screen

**When** I tap the bin icon

**Then** the corresponding item is removed fro the screen and from the DB

<br/> <br/>
**Given** I am on the Add Location Screen

**When** I tap a text box

**Then** the keyboard pops up 

<br/> <br/>
**Given** I am on the Add Location Screen

**When** I tap the "save new location" button

**Then** The location is added to the DB

**AND** I am returned to the List Screen

<br/> <br/>
**Given** I am on the Map Screen

**When** I tap a marker

**Then** the location name is displayed

<br/> <br/>
### Additional Resources

Kanban
![image](https://github.com/Ada-Apprenticeships/part-b-mvp-of-a-full-stack-solution-60-FrankieBADA/assets/134061898/48be3493-89de-4a43-a0b7-98454b2bb4ce)

UML Diagrams
![uml2](https://github.com/Ada-Apprenticeships/part-b-mvp-of-a-full-stack-solution-60-FrankieBADA/assets/134061898/ca253ba2-13d2-4fb6-a5af-e2cea38c68c8)
![LocationStateUML](https://github.com/Ada-Apprenticeships/part-b-mvp-of-a-full-stack-solution-60-FrankieBADA/assets/134061898/cead4b88-2633-41fa-9549-c295ae75b5c8)
![locations](https://github.com/Ada-Apprenticeships/part-b-mvp-of-a-full-stack-solution-60-FrankieBADA/assets/134061898/acd4c73c-e91f-42ae-bcf5-1a23c1fc0dd5)
![locationdb umls](https://github.com/Ada-Apprenticeships/part-b-mvp-of-a-full-stack-solution-60-FrankieBADA/assets/134061898/db06b148-fbef-4f01-8fed-b74878a19bc3)

