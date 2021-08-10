# Movie Gallery
A simple android app that shows movie list with search function which fetch movie list from api or db.

### Implemented by Clean Architecture
The structure of this project with 4 layers:
- Ui
- Presentation
- Domain
- Data

### Movie List Screen
<br>
<p>
  <img src="https://github.com/swezinlinn/Movies/blob/main/ss1.jpg" width="200"/>

  <img src="https://github.com/swezinlinn/Movies/blob/main/ss2.jpg" width="200"/>
</p>
<br>

### MovieListFlow
<br>
<p>
  <img src="https://github.com/swezinlinn/Movies/blob/main/ss1.jpg" width="200"/>

  <img src="https://github.com/swezinlinn/Movies/blob/main/ss2.jpg" width="200"/>
</p>
<br>

### Movie Detail Screen
<br>
<p>
  <img src="https://github.com/swezinlinn/Movies/blob/main/ss3.jpg" width="200"/>

  <img src="https://github.com/swezinlinn/Movies/blob/main/ss4.jpg" width="200"/>
</p>
<br>

### Communication between layers

1. UI calls method from ViewModel.
2. MovieListViewModel execute repo for connection with paging source which fetch data from api or db
3. MovieDetailViewModel execute UseCase which combines data from Repository
4. Each Repository returns data from a Data Source (Cached or Remote).
5. Information flows back to the UI where we display the list of movies or detail of movies.
