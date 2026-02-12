# Self-Tuning LSM Tree (Java)

## Overview

This project implements a simplified **Self-Tuning Log-Structured Merge (LSM) Tree** in Java.

The system dynamically optimizes:

- Compaction strategy (Size-Tiered vs Leveled)
- Read/Write workload balance
- Storage tier placement (Hot vs Cold)

It demonstrates core storage engine concepts used in modern distributed databases.

---

## Architecture Components

- **MemTable** – In-memory write buffer
- **SSTable** – Immutable sorted storage structure
- **CompactionEngine** – Executes compaction strategies
- **WorkloadMonitor** – Detects read-heavy vs write-heavy patterns
- **TierManager** – Manages HOT/COLD storage tier placement
- **LSMTree** – Coordinates all components

---

## Self-Tuning Behavior

### Write-Heavy Workload
- Switches to Size-Tiered Compaction
- Reduces write amplification

### Read-Heavy Workload
- Switches to Leveled Compaction
- Reduces read amplification

### Storage Tiering
- Frequently accessed SSTables → HOT tier
- Rarely accessed SSTables → COLD tier


