import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import './login-form.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';
import '@polymer/iron-icon/iron-icon.js';

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
<vaadin-vertical-layout style="width: 100%; height: 100%;" id="vaadinVerticalLayout"></vaadin-vertical-layout>
<vaadin-text-field placeholder="Search" id="vaadinTextField">
 <iron-icon icon="lumo:search" slot="prefix" id="ironIcon"></iron-icon>
</vaadin-text-field>
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
