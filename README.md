# GeneRLs: A Reinforcement Learning AI for Generals.io

> ⚠️ **This project is currently in its infancy.** At this stage, it only implements basic socket communication between Processing (Java) and Python.

## 🚀 Project Overview

The ultimate goal of **GeneRLs** is to build a reinforcement learning-powered AI capable of playing the strategy game [Generals.io](https://generals.io/) at a competitive—possibly even superhuman—level.

The AI will use deep reinforcement learning, trained through self-play simulations, to master the game's strategy, expansion, and combat mechanics.

## 🎯 Objectives

- Implement robust and efficient communication between Processing (visuals and game logic) and Python (reinforcement learning).
- Train the AI using reinforcement learning algorithms (initially PPO or DQN) through self-play.
- Scale training progressively from smaller maps and 1v1 scenarios up to larger, multi-player free-for-all battles.
- Aim for superhuman-level strategic gameplay, ultimately capable of challenging top human players.

## 🛠️ Current Status

- ✅ **Socket-based communication** established between Processing (client) and Python (server).
- ✅ Basic two-way communication implemented, enabling real-time exchange of structured game data.
- ⚠️ **Game logic and reinforcement learning training**: In progress.

## 📌 Tech Stack

- **Processing (Java)**: Game simulation and visualization.
- **Python**: Reinforcement learning logic, using libraries like PyTorch.
- **Socket Communication**: TCP-based efficient inter-language data exchange.

## 📂 Project Structure

```
GeneRLs/
├── src
│   ├── main
│   │   ├── java (Processing visuals and logic)
│   │   └── python (AI and socket server)
│   └── library (Processing dependencies, e.g., ffmpeg, video exports)
├── gradle scripts (build system)
└── settings and configurations
```

## ⚙️ How to Run

(Currently minimal setup)

1. **Start Python server:**
```bash
python server.py
```

2. Launch the Processing sketch (`Main.java`) through the Processing IDE or IntelliJ IDEA.

## 🗺️ Next Steps

- Implement a minimal, playable Generals.io game environment in Processing.
- Integrate basic reinforcement learning loops in Python.
- Scale training complexity gradually, aiming for increasingly sophisticated strategies.

## 📈 Roadmap

- [ ] Game logic and visual environment implementation
- [ ] Basic reinforcement learning setup (PPO/DQN)
- [ ] Self-play training environment
- [ ] Performance evaluation and strategic analysis