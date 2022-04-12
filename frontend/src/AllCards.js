import logo from './logo.svg';
import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import './AllCards.css';

class AllCards extends Component {

    constructor(props) {
        super(props);
        this.state = {cards: []};
    }

    componentDidMount() {
        fetch('/v1/creditcards/getAll')
            .then(response => response.json())
            .then(data => this.setState({cards: data}));
    }


    render() {
        const {cards} = this.state;

        const cardsList = cards.map(card => {
            return <tr key={card.cardNumber}>
				<td style={{whiteSpace: 'nowrap'}}>{card.cardHolderName}</td>
				<td style={{whiteSpace: 'nowrap'}}>{card.cardNumber}</td>
				<td style={{whiteSpace: 'nowrap'}}>£{card.balance}</td>
                <td style={{whiteSpace: 'nowrap'}}>£{card.cardLimit}</td>
            </tr>
        });
		
		return (
            <div>
                <Container fluid>
                    <table  class="cardsTable">
                        <thead >
                        <tr>
                            <th width="35%">Name</th>
                            <th width="35%">Card Number</th>
                            <th width="35%">Balance</th>
							<th width="35%">Limit</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cardsList}
                        </tbody>
                    </table>
                </Container>
            </div>
        );
    }
}


export default AllCards;
