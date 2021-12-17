# Android Clean Architecture

[Google Clean Architecture](https://android-developers.googleblog.com/2021/12/rebuilding-our-guide-to-app-architecture.html)

### `Please Read below to get the most out of this project...`

### _What can you expect?_
**In this repository you will find a sample project that sets up the ground for a Clean Architecture implementation in Android.**
Note: This implementation is _**my personal**_ interpretation of Uncle Bob's Clean Architecture recommendations, combined with some other techniques that based on my experience results in a very clean, simple, scalable, testable and maintainable project.

### _What this repo is not?_
This is not a first point of contact with Clean Architecture, there are already plenty of articles, books and material in general about the philosophy that you could use to get familiar with the concept.
Instead, you will see the digest of some experiences I've had before working with Clean Architecture along with key points worth highlighting about the topic. So, it is fair to say that the content provided here expects you to at least know the basics about Clean Architecture and with that said, let's jump into it.

# Introduction
The structure of the code in this repo could be used as a template for any project where you want to have:
- MVVM
- Hilt/Dagger
- Clean Architecture
- Basic Modularization

# How does this code promote Clean Architecture?
As you might already know Clean Architecture is the philosophy that tries to organize the elements that conform your Software Design into layers or ring levels that separate the different concerns of your logic with a very strong focus on the domain of your application.
So, to answer the question _**How does this code promote Clean Architecture?**_ Well, you will notice that the project is modularized into the following libraries:
- app (Android library)
- data (Android Library)
- domain (Pure Java/Kotlin library)

By creating these set of modules we can be sure that our code is already organized and separated in a way where you as a developer can get an idea of the right place for your logic. If you do not know exactly where to put it, hopefully this can help out, say we will write an app for a Book Store:
- app -> Presentation related logic (Activities / Fragments / ViewModels / etc)
- data -> Data processing related logic (DB / Retrofit / RemoteBookDto / LocalBookDBEntity / RemoteBookRepository / LocalBookRepository /etc)
- domain -> Things that represent your business logic, then... ( Book / GetBooksByTitleUseCase / BookRepository / etc)

Notice that the **domain** layer contains mostly object representations(like Book) and it's all about contracts/interfaces like (BookRepository). However, the actual implementation of that repository will be implemented by the code in the data layer, this separation in a loosely coupled manner is thanks to **Dependency Inversion**. _Dive into the project's code for a deeper understanding..._

# Now that the code is layered into different modules, I'm done?
Not at all... The most common mistake I've seen so far with this philosophy is to think that just because you are separating into different modules or packages _(app/presentation, data and domain)_, we are done. However, that's not the case and it is very important to mention that this isn't only about separating into modules, **the way those modules depend on each other also determines how well the Clean Architecture principles will be used in your project** Most importantly, the dependency relation will be a key factor on how well your code base will age and how easily you might violate some of the principles that will turn into technical debt eventually.

#### The most common misuse of the dependencies happens in _app_ :
`app (build.gradle)`
```groovy
    dependencies {
        //Clean Architecture dependencies
        implementation project(':domain')
        implementation project(':data')
        ...
```
#### Notice something interesting?
Well, Clean Architecture specifies the following interaction flow:

> _Presentation -> Domain -> Data_

Which is kind of easy to interpret as having both _(domain and data)_ dependencies into the presentation layer. However, there's a potential problem here... by having the **data module** available in the presentation layer, it might happen _(will happen)_ that a non experienced developer, starts using things that belong to **data** on the **presentation** module walking around domain, hence, causing a tightly coupled design when the presentation layer starts to do some of the following actions:
- Uses Response Dtos directly on Views (RecyclerViews)
- Uses DB Entity objects directly on presentation processes.
- Directly executes Network Calls or Database Queries from ViewModels _(in the best cases)_ or from Fragment/Activities _(in the worse cases)_

### But is this really so terrible?

Well, is a poor implementation of Clean Architecture that might bring a lot of inflexibility and technical debt to your code, since the whole point of separating the concerns is not only at a file tree level, but hopefully also at a concepts/aspects logical level. So, how do we do it then?
Well, **Dependency Injection helper modules** to the rescue _(Look at the project for a deeper understanding)_.
In our project, the modules have the following relationship:

`app (build.gradle)`
```groovy
    dependencies {
        //Clean Architecture dependencies
        implementation project(':domain')
        implementation project(':usecase-di')
        ...
```
`data (build.gradle)`
```groovy
    dependencies {
        //Clean Architecture dependencies
        implementation project(':domain')
        ...
```
`domain - doesn't depend on data nor presentation`

### What do we get out of this setup?
Well, by having a **_(usecase-di)_** module, we can completely remove the dependency **presentation** had on **data**, because this module will basically depend on both **data** and **domain** in order to provide us with the actions _(usecases)_ required by our presentation layer. Also it helps us hide all the details of the data layer and forces us to work only with the abstractions provided by the domain module. Most of the time the _usecases_ will be injected directly into the ViewModel and with that we complete a full separation not only in the file system but also logic wise.
From now on each of our modules can scale independently.

# Final Notes
Please don't take these concepts as absolute truth. The idea presented here is really based on the things that have worked for me in the past and in no way I'm assuming that it will work for every single project in the world. If you have any comments or would like to discuss further certain scenarios, please read the section below.

## Discussion
If you would like to start a discussion or leave a comment about the implementation in this project, please let's follow up [here](https://github.com/MartinCazares/android-advanced-clean-architecture/issues)
