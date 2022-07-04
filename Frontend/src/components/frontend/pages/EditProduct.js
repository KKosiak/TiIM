import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import Navbar from '../../../layout/frontend/Navbar'

class EditProduct extends Component {

    emptyItem = {
        productID: 0,
        name: '',
        price: '',
        amount: '',
        categoryID: 0
    };
    
    
    constructor(props) {
        super(props);
        this.state = {categories: [], item: this.emptyItem, };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            fetch(`http://localhost:8080/categories/${this.props.match.params.id}/product/${this.props.match.params.id}`)
                .then(response => response.json())
                .then(data => this.setState({item: data}));
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const title = target.title;
        let item = {...this.state.item};
        item[title] = value;
        this.setState({item});
    }


async handleSubmit(event) 
{
    event.preventDefault();
    const {item} = this.state;
    if (this.props.match.params.id !== 'new') {
        item.id = this.props.match.params.id;
    }
    await fetch('http://localhost:8080/categories' + (item.categoryID ? '/' + item.categoryID: '') + '/product' + (item.id ? '/' + item.id : ''), {
        method: (item.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        
        body: JSON.stringify(item),
    });
    this.props.history.push('/products');
}

render() {
    const {item} = this.state;
    const title1 = <h2>{item.categoryID ? '' : 'Nieprawidłowe ID_Kategorii'}</h2>
    const title = <h2>{'Dodaj Produkt'}</h2>;
    return <div>
        <Navbar/>
        <Container>
            {title}
            <Form onSubmit={this.handleSubmit}>
            <FormGroup>
                    <Label for="productID">ID_Produktu</Label>
                    <Input type="text" title="productID" productID="productID" value={item.productID || ''}
                           onChange={this.handleChange} autoComplete="productID"/>
                </FormGroup>
                <FormGroup>
                    <Label for="name">Nazwa</Label>
                    <Input type="text" title="name" productID="name" value={item.name || ''}
                           onChange={this.handleChange} autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Label for="price">Cena</Label>
                    <Input type="text" title="price" productID="price" value={item.price || ''}
                           onChange={this.handleChange} autoComplete="price"/>
                </FormGroup>
                <FormGroup>
                    <Label for="amount">Ilość</Label>
                    <Input type="text" title="amount" productID="amount" value={item.amount || ''}
                           onChange={this.handleChange} autoComplete="amount"/>
                </FormGroup>
                 <FormGroup>
                    <Label for="categoryID">ID_Kategorii</Label>
                    <Input type="text" title="categoryID" productID="categoryID" value={item.categoryID || ''}
                           onChange={this.handleChange} autoComplete="categoryID"/>
                </FormGroup>       
                {title1}     
                <FormGroup>
                    <Button color="primary" type="submit">Zapisz</Button>{' '}
                    <Button color="secondary" tag={Link} to="/product">Anuluj</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>
}

}
export default withRouter(EditProduct);