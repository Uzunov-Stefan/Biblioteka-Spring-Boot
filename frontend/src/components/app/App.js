import logo from '../../logo.svg';
import './App.css';
import React, {Component} from "react";
import Books from "../Books/books";
import libgenService from "../../repository/libgenRepository";

class App extends Component{

    constructor(props) {
        super(props);

        this.state = {
            books: []
        }
    }

    render() {
        return(
            <div>
                <Books books={this.state.books}/>
            </div>
        );
    }

    loadBooks = () => {
        libgenService.fetchBooks().then((data) => {
            this.setState({
                books: data.data
            })
        });
    }

    componentDidMount() {
        this.loadBooks();
    }
}

export default App;
