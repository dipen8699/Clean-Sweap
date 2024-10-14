# Clean-Sweap
In this project, we are tasked with implementation of the Clean Sweep robotic vacuum cleaner control system. Along with the implementation of the Clean Sweep control system, we are also tasked with create a Sensor Simulator to simulate the interactions the Clean Sweep will have within a home.
# Team Members
* Dipena Kanani
* Sai Kalyan Naidu
* Vijay Kumar Ragula
* Sherelyn Saceda
* Wasiullah Khalid
# Features
## Clean Sweep
### Control System
#### Navigation
The Clean sweep is able to navigate North, East, South, and West with the ability to detect obstacles and stairs.
<br/>Furthur implementations: 
* Inclusion of walls in detection to allow optimized navigation
#### Dirt Detection & Cleaning
The clean sweep has a capacity of 50 units. Each vacuum usage removes 1 unit of dirt.
<br/>Furthur Implementation: 
* Return to charging station when full
#### Power Management
The clean sweep's power is depleted based on floor type:
* Bare Floor - 1 unit
* Low-pile carpet - 2 units
* High-pile carpet - 3 units
The clean sweep is able to keep tabs on its power health, so when low health, it is able to navigate back to charging station.
#### Diagnostiscs and Troubleshooting
In furthur implementation. . .
#### Activity Log
Currently the console log with relay current position, current power, dirt capactity, and movement
<br/>Furthur Implementation:
* Weekly logs
* Totals
* Current Status
## Sensor Simulator
### Floor Plan
#### Layout
Created each cell and different variables to hold different values of:
* Position
* Surface Type
* Dirt on cell
* If it contains Obstacle/Stairs
* If it contains a charging station
#### Surfaces
All three surfaces are defined:
1. Bare floor (e.g. wood, linoleum, etc.)
2. Low-pile carpet
3. High-pile carpet
#### Navigation
To help facilitate navigation, the floor plan's cells indicate if it is free or has an obstacle or stairs.
<br/>Furthur implementation: 
* Inclusion of walls to provide a true simulation of a floor plan
#### Charging Stations
Cell(s) will contain a charging station. 
<br/> Furthur Implemenation: 
* Detection of charging within 2 cells
* Continuous tracking of nearest charging station
