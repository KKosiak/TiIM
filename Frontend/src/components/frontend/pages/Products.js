import React from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import Navbar from '../../../layout/frontend/Navbar'
import { Link } from 'react-router-dom';

class Products extends React.Component {

    constructor(props) {
        super(props);
        this.state = {products: [],categories: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount(){
        fetch('http://localhost:8080/product')
            .then(response => response.json())
            .then(data => this.setState({products: data}));
    }

    async remove(productID, categoryID) {
        await fetch(`http://localhost:8080/categories/${categoryID}/product/${productID}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedProducts = [...this.state.products].filter(i => i.productID !== productID);
            this.setState({products: updatedProducts});
        });
    }

    render() {
        
         const productList = this.state.products.map(product => {
             console.log({product})
             return <tr key={product.productID}>
                 <td>{product.productID}</td>
                 <td style={{whiteSpace: 'nowrap'}}>{product.name}</td>
                 <td>{product.price}</td>
                 <td>{product.amount}</td>
                 <td>{product.categoryID}</td> 
                 <td>
                     <ButtonGroup>
                         <Button size="sm" color="primary" tag={Link} to={"/products/"+product.productID}>Edytuj</Button>
                         <Button size="sm" color="danger" onClick={() => this.remove(product.productID, product.categoryID)}>Usuń</Button>
                     </ButtonGroup>
                 </td>
             </tr>
         });

        return (
            <div>
                <Navbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/products/new">Dodaj produkt</Button>
                    </div>
                    <h3>Produkty</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">ID_produktu</th>
                            <th width="40%">Nazwa</th>
                            <th width="15%">Cena</th>
                            <th width="15%">Ilość</th>
                            <th width="20%">Kategoria</th>
                        </tr>
                        </thead>
                        <tbody>
                        {productList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Products;