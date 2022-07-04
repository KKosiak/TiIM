import React from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import Navbar from '../../../layout/frontend/Navbar'
import { Link } from 'react-router-dom';

class Categories extends React.Component {

    constructor(props) {
        super(props);
        this.state = {categories: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/categories')
            .then(response => response.json())
            .then(data => this.setState({categories: data}));
    }

    async remove(categoryID) {
        await fetch(`http://localhost:8080/categories/${categoryID}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedCategories = this.state.categories.filter(i => i.categoryID !== categoryID);
            this.setState({categories: updatedCategories});
        });
    }

    render() {
        const categoryList = this.state.categories.map(categories => {
            return <tr key={categories.name}>
                <td>{categories.categoryID}</td>
                <td style={{whiteSpace: 'nowrap'}}>{categories.name}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/categories/" + categories.categoryID}>Edytuj</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(categories.categoryID)}>Usuń</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <Navbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/categories/new">Dodaj Kategorię</Button>
                    </div>
                    <h3>Kategorie</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <td width="20%">ID_Kategorii</td>
                            <th width="80%">Nazwa</th>
                        </tr>
                        </thead>
                        <tbody>
                        {categoryList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Categories;