# BlockRace

## Overview

**BlockRace** is a Minecraft plugin designed for Spigot servers that introduces a competitive mini-game where players race to find and interact with specific blocks within a given time frame. The plugin manages the game logic, player interactions, and integrates with Minecraft's built-in mechanics to provide a seamless gaming experience.

## Why the current Plugin Design is Flawed
This was programmed by me in early 2021, and I for some reason ensured it required a server restart to clear the game data (completely unnecessary), even though it had no implemented methods to reset the world. Currently you must run `/game reset` in order to restart the server, and delete the world (if necessary) during that period. This is an easy fix and will be addressed in the future.

## Features

- **Game Management**: Handles game state, player settings, and timing.
- **Dynamic Block Selection**: Automatically selects blocks that players need to find based on predefined criteria.
- **Action Bar Notifications**: Displays real-time game information in the action bar.
- **Boss Bar**: Shows game progress and block information using a boss bar.
- **Metrics**: Includes integration with bStats for tracking plugin usage and performance.

## Installation

1. Download the latest release of the `BlockRace` plugin from the [releases page](#).
2. Place the `BlockRace.jar` file into the `plugins` folder of your Spigot server.
3. Restart or reload your server to enable the plugin.

### THIS PLUGIN WILL NOT BE RELEASED UNTIL THE ABOVE DESIGN FLAW HAS BEEN ADDRESSED IN THE FUTURE.

## Configuration

`BlockRace` does not come with a default configuration file. It is designed to work out-of-the-box with its default settings.

## Commands

- **/game**: Start the BlockRace mini-game. Only available to players with the required permissions.

## Usage

1. **Starting the Game**: Use the `/game` command to start a new round of BlockRace. Ensure that you have the necessary permissions to start the game.
2. **Gameplay**: Once the game starts, players must race to find and interact with specific blocks. The current block and remaining time are displayed in the action bar and boss bar.
3. **Winning the Game**: The player who interacts with the most blocks within the time limit wins. The game will announce the winner once the time expires.

## Plugin Mechanics

- **Game Timing**: The game runs for a default duration of 1200 seconds (20 minutes).
- **Block Selection**: The plugin filters out blocks based on predefined criteria and selects the ones that players must find. Blocks such as sandstone, slabs, stairs, and other non-standard blocks are excluded.
- **Player Settings**: Each player has associated settings that track their progress and score throughout the game.

## Metrics

The plugin includes bStats integration, which allows for tracking usage statistics and plugin performance. The metrics can be reviewed [here](https://bstats.org/plugin/bukkit/BlockRace/10926).

## Development

To contribute or modify the plugin:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/BlockRace.git

2. Make your changes and improvements.

3. Create a pull request with a detailed description of your changes.

## Support

For any issues or support, please open an issue on the GitHub repository.

## License

This plugin is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements

- **bStats**: For providing analytics and metrics.
- **Spigot API**: For the Minecraft plugin development framework.