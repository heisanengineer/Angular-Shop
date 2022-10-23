import { Component } from "@angular/core";
import { Movie } from '../movie';
import { MovieService } from "../movie.service";

@Component({
    selector: 'movies',
    templateUrl: 'movies.component.html',
    styleUrls: ['./movies.component.css']
})
export class MoviesComponent {

    title = 'Movie List'
    movies!: Movie[];
    selectedMovie?: Movie;
    errorMessage: any;

    constructor(private movieService: MovieService) { }

    ngOnInit(): void {
        this.movieService.getMovies()
            .subscribe(
                (movies: Movie[]) => {
                    this.movies = movies;

                },
                (error: any) => this.errorMessage = <any>error);
    }

    onSelect(movie: Movie): void {
        this.selectedMovie = movie;
    }

    getMovies(): void {
        this.movieService.getMovies();
    }

    getTitle(): string {
        return this.title;
    }

    add(name: string, imageUrl: string, description: string): void {
        this.movieService.add({
            name,
            imageUrl,
            description
        } as Movie)
            .subscribe(movie => this.movies?.push(movie))
    }

    delete(movie: Movie): void {
        this.movies = this.movies.filter(m => m != movie);
        this.movieService.delete(movie).subscribe();
    }
}
