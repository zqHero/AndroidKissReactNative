 import React from 'react';
 import {
   AppRegistry,
   StyleSheet,
   Text,
   View,
   TouchableOpacity,
   Dimensions,
   NativeModules,
   ToastAndroid
 } from 'react-native';

export default class Communication extends React.Component {

  onPress = ()=> {
      // 这样 调用原生端  方法    原生来吐司
      NativeModules.CommunicationInterface.HandleMessage("Rn调用 原生 来吐司！！");
  }

   render() {
     return (
       <TouchableOpacity style={styles.container} onPress = {this.onPress.bind(this)}>
          <View style={{width:Dimensions.get('window').width,height:50,
          backgroundColor:'#dfd',alignItems:'center',justifyContent:'center'}}>
            <Text style={styles.hello}>点击Rn 调用原生Toast方法</Text>
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
