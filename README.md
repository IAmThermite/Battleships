**My Battleships game for my 3PR project 2016**

## TO DO
  * Get stakeholder feedback
  * Update help.txt - link to this page
  * Finish testing

## HOW TO PLAY
To start the game open up the ```Battleships.jar``` file. You will see this screen:

![Battleships Main Menu][HostJoin]

Enter a username up the top and then click on either ```Host``` or ```Join```.
If you click ```Host``` you will be brought to the Host screen. Here you enter a port number (1025-65535 inclusive). Give this port number as well as your IP address to your opponent. Then click ```Start```. The game will freeze until you opponent has connected.

![Battleships Host][Host]

To find you ip address type ```ipconfig``` into your console (or ```ifconfig``` on Linux or Mac OSX).

If you are click join, get the IP and port numbers from your opponent and enter them in to the correct fields.

![Battleships Join][Join]

You should now see this screen:

![Battleships Setup][Setup]

Either click on a grid location or choose a direction (either Down or Right) to begin, these can be done in any order. Then click on the ship that you want to place. If the ship will fit or doesnt overlap with another the ship will be placed. Once you have placed all five ships click ```Battle!```.

You will now see the main game board.

![Battleships Game][Game]

You will have to wait for your opponent to finish placing up their ships. When they have finished placing their ships one player will be choosen at random to start. You will be notified as to who starts in the Battle Log in the top right square.

Click on a panel in the lower grid and then click fire. If you hit a ship you will get another go, if you miss it will be your opponents turn.

The winner is the first to destroy all ships.

Enjoy!


[HostJoin]:images/1.png
[Host]:images/2-1.png
[Join]:images/2-2.png
[Setup]:images/3.png
[Game]:images/4.png
