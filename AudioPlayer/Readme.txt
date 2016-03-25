Developed in Eclipse with Android SDK
=====================================

For this project consists of two Android apps. The first app, named AudioServer stores a
number of audio clips, such as songs or other recordings. The clips are numbered 1 through n, where n is
the total number of clips. The app contains a service intended to be bound (as opposed to started), which
exposes an API for clients to use. The API supports such functionality as playing one of the audio clips,
pausing the clip, resuming the clip and stopping the playing of the clip altogether. In addition, this app
maintains an SQLite database that keeps track of all the requests that were received by the AudioServer app.
For each request, the database records the date and time (including seconds) when the request was issued,
the kind of request (e.g., whether to stop or resume a clip), the number of the clip (if applicable) and the
current state of the service when the request was received (e.g., playing clip number 3, paused while playing
clip number 4, etc.) Additional functionality exposed by the AudioServer API allows a client application to
query the database for all transactions that were recorded thus far in the database. The service broadcasts an
implicit intent when a clip finishes playing. The application some audio clips of variable
duration. 

The second app, PlayerClient consists of an activity that exposes functionality for using the AudioServer
and binds to the service for playing desired audio clips. The interface mininally includes appropriate
View elements for the following functionality: (1) Playing a given clip (by number), (2) Pausing the
playback, (3) Resuming the playback, (4) Stopping the player, and (5) getting a record of all AudioServer
transactions (i.e., requests) recorded so far. The transactions are shown in a second activity that contains a
ListView. When the client activity is stopped, the service still continues background playing; however, the service is unbound
and stopped if the activity is destroyed. Finally, when a broadcast is sent indicating that an audio clip has finished
playing, your app displays an appropriate toast message on the device’s screen.

Hints: The music files are included in the server app. When testing this application, make sure to upload
AudioServer app first, or else the client app may fail to initialize properly. 

Implementation notes: This project, uses a Nexus 5 device running the Android API 21—Lollypop platform. Backward compatibility 
with previous Android versions is not handled.
