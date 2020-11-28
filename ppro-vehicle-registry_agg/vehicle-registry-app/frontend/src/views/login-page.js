import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import './login-form.js';

class LoginPage extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<login-form id="loginForm"></login-form>
`;
    }

    static get is() {
        return 'login-page';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(LoginPage.is, LoginPage);
