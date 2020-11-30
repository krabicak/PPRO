import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-password-field.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

class LoginForm extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%; align-items: stretch; flex-direction: column; justify-content: center;" id="vaadinVerticalLayout">
 <vaadin-text-field label="Uživatelské jméno" id="username" style="align-self: stretch;"></vaadin-text-field>
 <vaadin-password-field label="Heslo" value="secret1" id="password" style="align-self: stretch;" has-value></vaadin-password-field>
 <vaadin-button theme="primary" id="login" style="align-self: stretch;">
   Přihlásit 
 </vaadin-button>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'login-form';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(LoginForm.is, LoginForm);
