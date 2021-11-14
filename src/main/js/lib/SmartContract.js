const shim = require('fabric-shim')             //引入shim-api

class Counter {
    async Init(stub) {                             //Init接口实现
        let buf = Buffer.alloc(4)
        buf.writeInt32LE(0)
        await stub.putState('counter', buf)          //写入状态counter
        return shim.success(Buffer.from('init'))    //返回调用端的信息
    }

    async Invoke(stub) {                           //Invoke接口实现
        let buf = await stub.getState('counter')    //读取状态counter的当前值
        let value = buf.readInt32LE(0)
        buf.writeInt32LE(value + 1)
        await stub.putState('counter', buf)          //更新状态counter的值为原值+1
        return shim.success(Buffer.from(`value updated => ${value + 1}`))  //返回调用端的信息
    }
}

shim.start(new Counter)