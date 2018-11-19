import React, { Component } from 'react';
import {Card, Row, Col, message, Button, Tabs, Input, Icon} from 'antd';

import food1 from '@/img/foods/food1.jpeg';
import food2 from '@/img/foods/food2.jpeg';
import food3 from '@/img/foods/food3.jpeg';
import food4 from '@/img/foods/food4.jpeg';
import food5 from '@/img/foods/food5.jpeg';
import food6 from '@/img/foods/food6.jpeg';
import food7 from '@/img/foods/food7.jpeg';
import food8 from '@/img/foods/food8.jpeg';
import food9 from '@/img/foods/food9.jpeg';
import food10 from '@/img/foods/food10.jpeg';
import food11 from '@/img/foods/food11.jpeg';
// import food12 from '@/img/foods/food12.jpeg';
// import food13 from '@/img/foods/food13.jpeg';
// import food14 from '@/img/foods/food14.jpeg';
// import food15 from '@/img/foods/food15.jpeg';

//商品分类函数
function showImg(k) {
    console.log(k);
    switch (k) {
        case "1":
            return food1;
        case "2":
            return food2;
        case "3":
            return food3;
        case "4":
            return food4;
        case "5":
            return food5;
        case "6":
            return food6;
        case "7":
            return food7;
        case "8":
            return food8;
        case "9":
            return food9;
        case "10":
            return food10;
        case "11":
            return food11;
        case "12":
            return food6;
        case "13":
            return food6;
        case "14":
            return food6;

        default:
            return food1;
    }
}
//吃的
function foodClassE (foodClass){
    let flag='none';
    if(foodClass==="吃的"){
        flag='block';
    };
    return flag;
}
//喝的
function foodClassD (foodClass){
    let flag='none';
    if(foodClass==="喝的"){
        flag='block';
    };
    return flag;
}

class menuCard extends Component {
    state = {
        username: '1',
        password: '1',
        foodData: [{
            name: '请稍等。。。',
            price:'99999.99',
            foodClass:'',
            image:'',
        }],
        display_Eat:"none",
        display_Drink:"none",
        // outCards: <div />,
    }

    componentDidMount=()=>{
        this.flash();
    }

    //发送ajax请求
    sendAjax = () =>{
        //POST方式,IP为本机IP
        fetch("http://localhost:8080/food/all", {
            method: "POST",
            //type:"post",
            //url:"http://39.106.56.132:8080/userinfo/tologin",
            mode: "cors",
            headers: {
                "Content-Type": "application/json;charset=utf-8",
            },
            body: JSON.stringify({
                username:'username',
                password:'password',
            }),
        }).then(function (res) { // function (res) {} 和 res => {}效果一致
            return res.json()
        }).then(json => {
            // get result
            const data = json;
            console.log(data);
            console.log(data.data.length);
            this.setState({
                foodData: data.data
            },function () {
            });
            console.log(this.state.foodData);
        }).catch(function (e) {
            console.log("fetch fail");
            alert('系统错误');
        });
    }
    //刷新渲染界面
    flash=()=>{
        this.sendAjax();
    }



    render() {
        const {
            foodData,

        } = this.state;


        return (
            <div >
                <Input className={'menu_search'} suffix={<Icon type="search"  />} defaultValue={'暂不可用'}/>
                <Button className={'menu_button'} onClick={this.flash}>（测试）刷新菜单</Button>
                <Tabs className={'menu_Tabs'} type="card">
                    <Tabs.TabPane tab="全部" key="1" className={'menu_Tabs'} >
                        {/**/}
                        <Row gutter={16}>
                            {
                                foodData.map((data,i)=>(
                                    <MyCard name={data.name} price={data.price} img={data.image} key={i} />
                                ))

                            }
                        </Row>
                    </Tabs.TabPane>
                    <Tabs.TabPane tab="吃的" key="2">
                        {/**/}
                        <Row gutter={16}>
                            {/*function () {
                                                console.log("chi");
                                                let flag='none';
                                                if(data.foodClass==="吃的"){
                                                    flag='block';
                                                };
                                                return flag;
                                            }*/}
                            {
                                foodData.map((data,i)=>(

                                    <MyCard name={data.name} price={data.price}  style={{display:foodClassE(data.foodClass)}} img={data.image} key={i}/>

                                ))

                            }
                        </Row>
                    </Tabs.TabPane>
                    <Tabs.TabPane tab="喝的" key="3">
                        {/**/}
                        <Row gutter={16}>
                            {
                                foodData.map((data,i)=>(
                                    <MyCard name={data.name} price={data.price} style={{display:foodClassD(data.foodClass)}} img={data.image} key={i}/>
                                ))

                            }
                        </Row>
                    </Tabs.TabPane>
                </Tabs>

            </div>
        );
    }
}

export default menuCard;



//单个卡片的组件
class MyCard extends Component{
    //发消息
    sendMsg=()=>{
        console.log(this.props.img);
        message.success("成功加入购物车！");
    }

    render(){
        return(
            <Col span={8} style={this.props.style} >
                <Card onClick={this.sendMsg} hoverable style={{ width: 240, margin: '30px' }} cover={<img alt="example" src={showImg(this.props.img)} />}>
                    <Card.Meta title={this.props.name} description={this.props.price + "元"} />
                </Card>
            </Col>
        );
    }

}