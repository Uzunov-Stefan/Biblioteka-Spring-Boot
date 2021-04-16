import axios from "../custom-axios/axios";

const libgenService = {
    fetchBooks: () => {
        return axios.get("/book");
    }
}

export default libgenService;
