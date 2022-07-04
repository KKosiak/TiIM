import React, { Component } from 'react';
import { Link, withRouter} from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import Navbar from '../../../layout/frontend/Navbar'

class EditCategories extends Component {

    emptyItem = {
        categoryID: 0,
        name: ''
    };
    tempItem = {
        tempID: 0
    }

    constructor(props) {
        super(props);  
        this.state = {
            item: this.emptyItem,
            item1: this.tempItem
        };
        this.handleChange = this.handleChange.bind(this);       
        this.handleSubmit = this.handleSubmit.bind(this);   
        
    }

    async componentDidMount() {

        if (this.props.match.params.id !== 'new') {
            fetch(`http://localhost:8080/categories/${this.props.match.params.id}`)
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

    await fetch('http://localhost:8080/categories'+(item.id ? '/' + item.id : ''), {  
        method: (item.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/categories');
}

render() {
    const {item} = this.state; 
    const title = <h2>{'Dodaj kategorie '}</h2>;
    return <div>
        
        <Navbar/>
        <Container>
            {title}
            <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="categoryID">ID Kategorii</Label>
                    <Input type="text" title="categoryID" categoryID="categoryID" value={item.categoryID || ''}
                           onChange={this.handleChange} autoComplete="categoryID"/>
                </FormGroup>
                <FormGroup>
                    <Label for="name">Nazwa</Label>
                    <Input type="text" title="name" categoryID="name" value={item.name || ''}
                           onChange={this.handleChange} autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Zapisz</Button>{' '}
                    <Button color="secondary" tag={Link} to="/categories">Anuluj</Button>
                </FormGroup>

            </Form>
        </Container>
    </div>
}


}
export default withRouter(EditCategories);