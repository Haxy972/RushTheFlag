# 🚩 RushTheFlag

**FRANCAIS**

RushTheFlag est un plugin fonctionnant sous spigot ``1.8`` vous permettant de réalisé une partie de RushTheFlag
Tout est fait pour que l'utilisateur comprenne le fonctionnement du plugin, une config **complète** lui est mis a disposition.
Les messages sont entièrement customisables, 1 langage est disponible actuellement

Ce GitHub permet le développement de ce plugin.

**ENGLISH**

RushTheFlag is a plugin running on spigot ``1.8`` allowing you to perform a part of RushTheFlag
Everything is done so that the user understands the functioning of the plugin, a **complete** config is made available to him.
Messages are fully customizable, 1 language is currently available.

This GitHub allows the development of this plugin.

# 🦜 Suggestions / Bugs

**FRANCAIS**

Vous pouvez partagez vos idées, bugs ou questions par apport au plugin dans [Issue Page](https://github.com/Haxy972/RushTheFlag/issues)
Ces demandes sont traités quand je suis disponible il se peut que je ne les voient pas tout de suite
Cela m'aidera a développer le plugin.

**ENGLISH**
You can share your ideas, bugs or questions by contributing to the plugin in [Issue Page](https://github.com/Haxy972/RushTheFlag/issues)
These requests are processed when I am available I may not see them right away
This will help me develop the plugin.

# 👇 Installer

**FRANCAIS**

La dernière version du plugin se trouve dans [Release Page](https://github.com/Haxy972/RushTheFlag/releases) , cela vous permet de voir les nouveautés également.

**ENGLISH**

The latest version of the plugin can be found in [Release Page](https://github.com/Haxy972/RushTheFlag/releases) so you can see what’s new as well.

# ➕ ABOUT / PLUS

**FRANCAIS**

Ce plugin a été développer par Haxy972, les contributions sont autorisés, le projet est open source.
``Veuillez tout de même a me mentionné en cas d'utilisation sur des serveurs Mini-Jeux ``

**ENGLISH**

This plugin was developed by Haxy972, contributions are allowed, the project is open source.
``Please tell me about use on Mini-Games servers ``

# 🟢 MISE A JOUR

**FRANCAIS**

La dernière version du plugin vient de sortir vous serrez mis au courant en cas de prochaines nouveautés ici même !

**ENGLISH**

The latest version of the plugin has just been released you will be updated in case of future novelties right here!

``# Made by Haxy972
# https://github.com/Haxy972/RushTheFlag <--- to see the config file explanations, good game !
prefix: "&b&lRush&f&lThe&b&lFlag&8> "
language: "EN"
# The red team has 1 player in more, it's very useful for test
# If you are solo go to the blue team to test
debug: false

# Game Settings
# Remember to reload your plugin after a modification of the config file

# FOR ALL PERMISSIONS ON THIS PLUGIN ADD: RushTheFlag.*

# ------------------------------------ | GAME OPTIONS | ------------------------------------#
#           The game options section is to modify some settings like spawns and timer       #
# ------------------------------------------------------------------------------------------#

game:
  world: "world"
  # Join is also the death teleportation

  spawnJoin:
    x: 0
    y: 0
    z: 0
    yaw: 0
    pitch: 0
  # Coordinates of spawn and nexus requires decimal (0.500)*
  #
  # IMPORTANT: Y value doesn't require 0.500 don't write ! Keep integer value
  #
  spawnBlue:
    x: 0
    y: 0
    z: 0
    yaw: 0
    pitch: 0
  spawnRed:
    x: 0
    y: 0
    z: 0
    yaw: 0
    pitch: 0
  # Nexus doesn't contain pitch and yaw
  nexusBlue:
    x: 0
    y: 0
    z: 0
  nexusRed:
    x: 0
    y: 0
    z: 0
  death-timer: 5

  team:
    # Here you need to set the name of players in the tab

    # To remove default grade please write "none"
    # REMARKS: On reload of the game the player keep it's team tablist name if is "none"
    # option: [{player} = player's name]

    tab-name-default: "&7{player}"

    tab-name-blue: "&9&lBleu &7{player}"
    tab-name-red: "&c&lRouge &7{player}"

    # Value (-1) is for remove team selection on player join
    # It's to equilibrate team, an player can't join a team if team + {value} are >= other team count
    count-tolerance: 2
    # Coins win with VoltEconomy
    win-team-max-coins: 20


# ------------------------------------ | OTHER OPTIONS | ------------------------------------#
#                        More options here to limit player action and movement               #
# -------------------------------------------------------------------------------------------#

# There is the region where you can build
regions:
  # Default value is 2, you can't build 2 blocks around the flag
  flag: 2
  # Default value is 2, you can't build 2 blocks around the flag
  # Integer is required here
  base:
    x: 5
    y: 4
    z: 2

blocks:
  # Allow infinite sandstone
  # On place of sandstone the amount of sandstone doesn't drop of
  infinite-sandstone: false

kits:
  # Number of rows of kit inventory
  inventory-rows: 1

# The player will be dead if his y-coordinate is under this value
altitude-min: 0

# This option is useful if you are using Permission like GroupManager, PermissionEx
# It can also be useful to add nametag to player with NameTagEdit
nametag:
  enabled: true
  command-group-red: "/manuadd {player} red"
  command-group-blue: "/manuadd {player} blue"

  # This parameter is to set default group name
  default-group-enabled: true
  # Here default is the group name
  default-group-onjoin: "/manuadd {player} default"


# Active default kit, they are generated every reload if they are deleted
default-kit: true


scoreboard-ip: "  &bMade by Haxy972"
# 0: Normal text
# 1: The text will flash every 0.5 seconds for 5 seconds and then pause for 3 seconds
scoreboard-animation: 1




``
