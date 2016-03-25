Developed in Eclipse and Android SDK
====================================
The main activity in the app displays thumbnail pictures of cars from various manufacturers in a grid layout.
The thumbnails are separated by thin spaces uniformly on all four sides. Also, size the thumbnails appropriately, e.g., using 3 or 4 columns
in portrait mode. 
Each thumbnail in the grid view supports two kinds of functionality. First, a simple click on a thumbnail
brings up a new activity that shows the entire picture of the selected car on the entire device display. The user
can return to the grid view by selecting the “back” soft button on the phone. A long click on any thumbnail
will bring up a “context menu” showing the following two options for the car under consideration: (1) View
the entire picture (similar to a simple click); (2) Show the official web page of the make and model for the
selected thumbnail in a new activity (e.g., a picture of a Honda Civic would bring up the Civic’s web page
in the official Honda web site).

Images and the respective web links are coded in the app. Cannot be changed without compiling and deploying again.

Environment notes: This project uses a Nexus 5 device running the API 21—Lollypop Android platform
available. Downward compatibility of with lower android version is not handled.