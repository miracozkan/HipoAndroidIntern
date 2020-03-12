## Hipo Android Intern
*It's a simple listing app, firstly getting members list from remote server than print screen and we can add new member which is the intern candidate also we can search all member with Search Bar*
#### Architecture
- MVVM

#### Technology Stack
- Kotlin
- Retrofit 2 : 
	*I used this library for network request and i chose that because it can work with coroutines by successfully*

- LiveData : 
	*it keeps all the time up-to-date data thanks to its base on the architecture of observer, there is no memory leak because when component dies which is LiveData was attached , LiveData will die also it is lifecycle-aware.*

- Dagger 2 : 
	*Dagger is a dependency injection library for managing class dependencies like repository classes or view models. I used it because I want to create a modular architecture although the project is small.*

- ViewBinding : 
	*It's announced last I/O and getting stable of Android Studio 3.6v. I chose it instead of kotlin synthetic because it provides us to a great feature which is null-safety*

- Coroutines : 
	*Coroutine is kotlin library for threading, it provides to us lightweight thread*

- Navigation Component
