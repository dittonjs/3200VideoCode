import React, { useState } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  Button,
} from 'react-native';


const App = () => {
  const [count, setCount] = useState(0);
  return (
    <SafeAreaView>
      <Text style={styles.text}>{count}</Text>
      <Button title="Increment" onPress={() => setCount(count + 1)}/>
      <Button title="Decrement" onPress={() => setCount(count - 1)}/>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  text: {
    fontSize: 100,
    textAlign: "center"
  },
});

export default App;
