import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
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
<vaadin-vertical-layout id="vaadinVerticalLayout" style="width: 100%; height: 100%;">
 <login-form id="loginForm" style="align-self: center; flex-grow: 0; flex-shrink: 1;"></login-form>
</vaadin-vertical-layout>
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
