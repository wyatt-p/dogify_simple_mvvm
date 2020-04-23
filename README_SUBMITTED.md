# Dogify


This is a simple *dogify* Android application. This application will query a list of dog breeds using the dog API. Then it will
populate the breed names and images associated with them in a RecyclerView grid. 

# Main Points/Features
- Using MVVM model
- Data binding
- Reactive programming (rxjava)
- Dagger2 for dependency injection
- Glide lib for image/cache processing
 
This implmentation is heavily focused on utilizing the (Google recommended) MVVM architecture. To make things easier, I've 
implemented a reactive component which utilizes RxJava and some of androids lifecycle aware architecture components such as 
*LiveData* and *Data-binding*. To make the application more modular and easier to test, I've leveraged the use of Dagger2 for
dependency injection.

# MVVM
Using the Model-View-ViewModel (with repo) architecture I have maintain a logical seperation of concerns amongest the business
logic and presentation logic. You will notice that my network calls lies in the model area (reference repo production areas) and
my UI logic resides in my View (Activity) and presentation based business logic lies in the view model. ViewModel is not aware of
the View but the View is aware of the ViewModel. Additionally, the Model is not aware of the ViewModel but the ViewModel is aware
of the Model.

# Dagger 2
Used for dependency injection, also used android supported lib of dagger 2 to help streamline and assist in injecting dependencies
for Android based components (App, Activites, Fragments etc...). Also very useful for testing though note the requirement did not
call for creating these tests but it would be very easy to implement given the architecture and use of DI.

# RxJava 2
Using reactive/functional programming framework to assist in emitting and observing events. I use RxJava 2 in the Model (Observables) and
in the ViewModel (mostly as Observers). 

# Data-Binding
I decided to use Androids data-binding functionality as opposed to something that could provide similiar assistance like Butterknife given
a couple of reasons. First, Butterknife uses reflection in its bindings and thus slower. Additionally, when coupled with LiveData it makes 
the UI more reactive and seemless.

# Glide
Mainly used to load dog breed images. The caching/re-fetching of image data seems to work better then Picasso for this application.

# Other
if you have questions, maybe wondering why I did this instead of that... Please feel free to reach out!

# Installation Important Notes
Note that I created (mainly) two different build flavors. One is production based while the other is for mock data. This is very nice when testing
different use cases and situations. While the production flavor is for the main application. Note that as of now, the mock variation will not contain
any data. The production variation is what you are after.

Production (Debug):
```sh
installProdDebug
```

Note that the mock variant will just have a spinner since I have not hooked up any data yet so please use the correct one. Thank you!
