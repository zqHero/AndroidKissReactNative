'use strict';
import React, { Component } from 'react';

import {
    AppRegistry,
} from 'react-native';

import HelloWorld from './communication1.js';
import Communication from './communication2.js';
import Communication3 from './communication3.js';

AppRegistry.registerComponent('Communication', () => Communication);
AppRegistry.registerComponent('MyReactNativeApp', () => HelloWorld);
AppRegistry.registerComponent('Communication3', () => Communication3);
