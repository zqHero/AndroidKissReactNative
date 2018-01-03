 import React from 'react';
 import {
   AppRegistry,
   StyleSheet,
   Text,
   View,
   TouchableOpacity,
   Dimensions,
   NativeModules,
   ToastAndroid,
   DeviceEventEmitter
 } from 'react-native';

export default class Communication3 extends React.Component {

      constructor(){
        super();
        this.state = {
            info : "我是Rn写的内容"
        }
      }

    componentWillMount(){
      DeviceEventEmitter.addListener('mEventName',
                           this.rnMethod.bind(this));
    }

    rnMethod(params){
      this.setState({info:params});
    }

   render() {
     return (
       <TouchableOpacity style={styles.container}>
          <View style={{width:Dimensions.get('window').width,height:50,margin:10,
              backgroundColor:'#dfd',alignItems:'center',justifyContent:'center'}}>
                <Text style={styles.hello}>{this.state.info}</Text>
          </View>
       </TouchableOpacity>
     )
   }
 }
 var styles = StyleSheet.create({
   container: {
     flex: 1,
     justifyContent: 'center',
   }
 });
