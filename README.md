# Fabric Simulation

A real-time 2D simulation of a fabric mesh using spring forces and gravity. The simulation demonstrates elastic deformation, force application, and interactivity with nodes that can be dragged and linked to simulate fabric behavior.

![gifff-ezgif com-optimize](https://github.com/user-attachments/assets/2761628f-8b11-4132-bc22-026954731139)


## 🔧 Key Features

### ⚙️ Simulation Dynamics
- **Gravity**: A constant force applied downward on the fabric mesh.
- **Springs**: Each node in the fabric is connected to its neighbors using springs. These springs behave according to Hooke's Law, generating forces based on their displacement.
- **Damping**: A damping force is applied to the fabric to simulate realistic friction or air resistance.
- **Node Interactions**: Nodes can be grabbed and moved interactively, and links between nodes can be toggled with a right-click.

### 🎮 Interactive Controls
- Left-click to grab and move nodes.
- Right-click to unlink nodes from their neighbors.
- Drag nodes to change the shape of the fabric in real-time.
- Real-time updates with a fixed time step for physics simulations to maintain stability across machines.

### 📈 Visualization
- **Node Rendering**: Each node is represented as a circle on the canvas, and springs are drawn between neighboring nodes.
- **Real-time Force Application**: Spring forces, gravity, and damping forces are visually reflected in the simulation.

### 🖱️ Mouse Interaction
- Click-drag mechanics for easy manipulation of individual nodes.
- Right-click to unlink nodes and disrupt connections.

## 🧰 Installation & Setup

### 🔨 Requirements
- Java 17+ JDK
- IDE with JavaFX support (Eclipse, IntelliJ, VS Code)

### ⚙️ Eclipse Configuration

#### Import Project:
**Option 1: Clone from Git**
```bash
git clone https://github.com/andreicscs/FabricSimulation.git
```
Then in Eclipse:

File → Import → Existing Projects into Workspace → Select root directory → Browse to the cloned folder
##

**Option 2: Download Project Folder**

File → Import → Existing Projects into Workspace → Select root directory → Browse to the downloaded folder

### Add JavaFX Libraries:
Right-click project → Build Path → Configure Build Path → Add External JARs (from JavaFX lib folder)

#### VM Arguments (Run Configurations):
--module-path "path/to/javafx-sdk-19/lib" --add-modules javafx.controls,javafx.fxml
> Replace `"path/to/javafx-sdk-19/lib"` with your actual JavaFX SDK path.

---
## 🎮 Usage Guide

### 🧪 Particle Creation and Manipulation
- **Left-click** - Select and drag nodes  
- **Right-click** - Unlink nodes from their neighbors  
- **Drag** - Change the shape of the fabric  

### 📋 Control Panel (Right Sidebar)
| Component          | Functionality                              |
|--------------------|--------------------------------------------|
| Left-click         | Grab and move nodes                        |
| Right-click        | Unlink nodes                               |
| Drag nodes         | Change the shape of the fabric             |
| Fabric Interaction | Real-time simulation with interactive updates |

## 🧱 Technical Architecture

### 🧵 Physics Simulation
- **Spring Forces**  
  Based on Hooke's Law, calculated for neighboring nodes  
- **Gravity**  
  Constant downward force on all nodes  
- **Damping**  
  Resistance force on node velocities to simulate friction and stabilize the simulation

### 🖼️ UI Framework
- **JavaFX**  
  Renders fabric mesh and handles node interactions  
- **Swing MouseListeners**  
  Processes click and drag interactions  
