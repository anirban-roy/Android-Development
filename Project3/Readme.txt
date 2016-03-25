Developed in Android Studio
===========================

This project utilizes the android fragments concept. There are 2 fragments in the activity.The first fragment
contains a 3-item list of topics: Cars, Peoples and Landscapes and a status button. The second fragment is initially empty.
When a user selects one of the items in the first fragment, the app downloads 6 pictures in the selected topic. For instance,
if the topic is “cars” the app downloads six pictures of cars from the web. The status button
displays a short toast message indicating the current status of the application (e.g., “idle”, “downloading
pictures”, “showing downloaded thumbnails”, and “showing selected picture”). This button remains responsive while the pictures are downloaded.
To achieve this, AsyncTasks are used to download the images through background threads.
Once the six pictures are downloaded, appropriately scaled (and possibly cropped) versions of the pictures
are displayed in a table layout contained in the second fragment. The user may select one of the six
pictures, by clicking on the picture, in which case the entire fragment display is replaced with the picture.
The two fragments are displayed on top of each other (with the list fragment above the picture
fragment) when the device is in portrait mode and side-by-side (with the list fragment to the left of the picture
fragment) when the device is in landscape mode. Configuration changes are handled seamlessly
(i.e., the picture fragment does not “lose” its pictures while it is rotated.) Finally, fragment events (e.g.,
switching from the table view of thumbnails to the full view of a single picture) should be recorded in the
back stack. Either way, the first fragment should take about 25-35% of the device’s display with the picture
fragment taking the remaining portion of the device’s display.

Implementation notes: For this project used a Nexus 5 device running the Android API 21—Lollypop. Backward
compatibility with previous Android versions is not provided.