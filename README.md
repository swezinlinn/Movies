# Movie Gallery
A simple android app that shows movie list with search function which fetch movie list from api or db.

### Implemented by Clean Architecture
The following diagram shows the structure of this project with 4 layers:
- Ui
- Presentation
- Domain
- Data

<br>
<p align="center">
  <img src="https://github.com/ZahraHeydari/ArtGallery/blob/master/diagram.png" width="750"/>
</p>
<br>

### Communication between layers

1. UI calls method from ViewModel.
2. ViewModel executes Use case.
3. Use case combines data from Album and Photo Repositories.
4. Each Repository returns data from a Data Source (Cached or Remote).
5. Information flows back to the UI where we display the list of posts.