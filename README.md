# Cinemax
Cinemax is a modern Android application for exploring movies, categories, and details, powered by the [TMDB API](https://www.themoviedb.org/). The project demonstrates modern Android development practices, utilizing a multi-module architecture, Clean Architecture principles, and an offline-first approach.
## 🚀 Features
- **Home Screen**: Browse Now Playing movies, popular movies filtered by genre, and movie categories.
- **Movie Details**: View in-depth details of a movie, including runtime, genres, and overviews.
- **Offline-First Experience**: Data is cached locally using Room, allowing the app to function even without an active internet connection.
- **Modern UI**: Built entirely with Jetpack Compose, offering a reactive and fluid user interface.
## 🛠️ Technology Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture**: MVVM, Clean Architecture
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
- **Network**: Retrofit & OkHttp (with Bearer Token Authentication)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room) (with `@Embedded` and `@Relation` handling)
- **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Data Source**: TMDB API
## 🏗️ Architecture
The app follows the official **Now in Android** multi-module architecture and **Clean Architecture** guidelines:
### Modules
- `app`: Connects all feature modules and sets up the application graph.
- `feature:*`: Contains specific app features (e.g., `feature:home`, `feature:auth`). Each feature handles its own UI and presentation logic (ViewModels).
- `core:data`: Implements repositories, local database (Room) handling, and network data fetching. Acts as the Single Source of Truth.
- `core:domain`: Contains UseCases encapsulating business logic (e.g., `SyncMovieDetailsUseCase`, `GetMovieDetailsUseCase`).
- `core:model`: Defines domain data models.
- `core:designsystem`: Houses reusable UI components, themes, and styling.
- `core:network`: Manages Retrofit API interfaces and OkHttp configurations.
- `core:ui`: Contains reusable Compose UI components across different features.
- `core:common`: Core utility functions and classes.
### Data Flow (Offline-First)
The application prioritizes local data to ensure a seamless experience. 
1. The UI observes a continuous `Flow` of data from the database via `Get...UseCase`.
2. To update data, a `Sync...UseCase` fetches data from the TMDB API.
3. The retrieved data is persisted into the Room database.
4. The Room database acts as the single source of truth, automatically emitting updated values back to the UI through the previously established Flow.
## ⚙️ Setup and Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/SNilesh2/cinemax-app.git
   ```
2. Open the project in Android Studio.
3. Configure your TMDB API token (Ensure you have a read access token).
4. Build and run the project on an emulator or physical device.
## 📝 License
This project is licensed under the MIT License.
