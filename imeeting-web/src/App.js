import React, { Component } from 'react';
//import logo from './logo.svg';
//import ReactDOM from 'react-dom';
import {Button, Drawer, Icon, Input, Layout, Menu, message,Select} from 'antd';
import {BrowserRouter as HashRouter, Route, Link} from 'react-router-dom';
// import Head from '@/pages/Head';
import Welcome from '@/pages/Welcome';
import B_O_Add from '@/pages/booking/BookingOfAdd';
import B_O_HY from '@/pages/booking/BookingOfHY';
import B_O_Time from '@/pages/booking/BookingOfTime';
import '@/css/Layout.css';
import '@/App.css';
import logo from "@/img/logo/logo1024.png";
import FindHY from "./pages/user/FindHY";
import menuAll from "./pages/menu/menuAll";
import menuCard from "./pages/menu/menuCard";

class App extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            display_GLY:'none',
            display_User:'none',
            display_Visitor:'block',
            display_name: 'block', //此状态机为display的取值
            menu_mode:'inline',//vertical
            width: '200px',
            collapsed:true,

        }
    }
    changeMode(msg) {
        if(msg==="管理员模式"){
            this.setState({
                display_GLY:'block',
                display_User:'none',
                display_Visitor:'none',
            });
        }else if(msg==="游客模式"){
            this.setState({
                display_GLY:'none',
                display_User:'none',
                display_Visitor:'block',
            });
        }else if(msg==="用户模式"){
            this.setState({
                display_GLY:'none',
                display_User:'block',
                display_Visitor:'none',
            });
        }else {
            console.log("错误！未监测到身份");
            this.setState({
                display_GLY:'none',
                display_User:'none',
                display_Visitor:'block',
            });
        }
    }
    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    }
    render() {
        return (
            <div className="App">
                <HashRouter>
                <Layout>
                    <Layout.Header className={'Head'}>
                        <Head changeMode = {(msg) => this.changeMode(msg)} ></Head>
                    </Layout.Header>
                    <Layout>
                        {/************************************左边菜单栏************************************/}
                        <Layout.Sider trigger={null} collapsible collapsed={this.state.collapsed} style={{color: '#fff',backgroundColor:'#fff'}}>
                            <Menu className='leftSider' mode={this.state.menu_mode} theme='light' style={{color: '#000',backgroundColor:'#ffffff'}}>
                                {/*<Menu.Item style={{display:this.state.display_name}}>*/}
                                {/*<span ><Icon type='home'/>Home</span>*/}
                                {/*<Button className='Siderbtn' onClick={this.display_name.bind(this)} ></Button>*/}
                                {/*<Button className='Siderbtn' onClick={this.menu_mode.bind(this)} style={{display:this.state.display_name}}></Button>*/}
                                {/*</Menu.Item>*/}
                                <Menu.Item onClick={this.toggle}>
                                    <Icon type='home' />
                                    <span >主页</span>

                                </Menu.Item>
                                {/*管理员功能模块*/}
                                <Menu.SubMenu  title={<span><Icon type='tool'/><span>企业管理</span></span>} style={{display:this.state.display_GLY}}>
                                    <Menu.Item><Link to='/book/HY'>员工管理</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>会员管理</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>餐桌管理</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>菜品管理</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>后台厨房管理</Link></Menu.Item>
                                </Menu.SubMenu>
                                <Menu.SubMenu  title={<span><Icon type="dollar" /><span>财务管理</span></span>} style={{display:this.state.display_GLY}}>
                                    <Menu.Item><Link to='/book/HY'>收入</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>支出</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>交易记录</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>交易图表</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>交易查询</Link></Menu.Item>
                                </Menu.SubMenu>
                                <Menu.SubMenu  title={<span><Icon type="appstore"/><span>其它</span></span>} style={{display:this.state.display_GLY}}>
                                    <Menu.Item><Link to='/book/HY'>日志管理</Link></Menu.Item>
                                </Menu.SubMenu>
                                {/*用户模式*/}
                                <Menu.SubMenu  title={<span><Icon type="bars" /><span>菜单</span></span>} style={{display:this.state.display_User}}>
                                    <Menu.Item><Link to='/menu/all'>全部</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY1'>类型1</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY2'>类型2</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY3'>类型3</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY4'>类型4</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY5'>我的购物车</Link></Menu.Item>
                                </Menu.SubMenu>
                                <Menu.SubMenu  title={<span><Icon type='team'/><span>餐厅信息</span></span>} style={{display:this.state.display_User}}>
                                    <Menu.Item><Link to='/book/HY'>餐桌占用情况</Link></Menu.Item>
                                </Menu.SubMenu>
                                <Menu.SubMenu  title={<span><Icon type='user'/><span>会员信息</span></span>} style={{display:this.state.display_User}}>
                                    <Menu.Item><Link to='/book/HY'>外卖地址</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>密码修改</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>消费记录</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>我要充值</Link></Menu.Item>
                                </Menu.SubMenu>
                                {/*游客模式*/}
                                <Menu.SubMenu  title={<span><Icon type="bars" /><span>菜单</span></span>} style={{display:this.state.display_Visitor}}>
                                    <Menu.Item><Link to='/menu/card'>全部</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY1'>类型1</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY2'>类型2</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY3'>类型3</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY4'>类型4</Link></Menu.Item>
                                    <Menu.Item><Link to='/user/findHY5'>我的购物车</Link></Menu.Item>
                                </Menu.SubMenu>
                                <Menu.SubMenu  title={<span><Icon type='team'/><span>餐厅信息</span></span>} style={{display:this.state.display_Visitor}}>
                                    <Menu.Item><Link to='/book/HY'>餐桌占用情况</Link></Menu.Item>
                                </Menu.SubMenu>
                                <Menu.SubMenu  title={<span><Icon type='user'/><span>会员信息</span></span>} style={{display:this.state.display_Visitor}}>
                                    <Menu.Item><Link to='/book/HY'>外卖地址</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>密码修改</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>消费记录</Link></Menu.Item>
                                    <Menu.Item><Link to='/book/HY'>我要充值</Link></Menu.Item>
                                </Menu.SubMenu>
                            </Menu>
                        </Layout.Sider>

                        {/************************************核心页面************************************/}
                        <Layout.Content className='contentLayout'>
                            <Route path={"/menu/all"} component={menuAll} />
                            <Route path={"/menu/card"} component={menuCard} />
                            <Route path={"/book/address"} component={B_O_Add} />
                            <Route path={"/book/time"} component={B_O_Time} />
                            <Route path={"/book/HY"} component={B_O_HY} />
                            <Route path={"/user/findHY"} component={FindHY} />
                            <Route path={"/welcome"} component={Welcome} />
                        </Layout.Content>

                    </Layout>
                        <Layout.Footer>
                            版权所有 @JGN-antd
                        </Layout.Footer>
                </Layout>
                {/*<header className="App-header">*/}
                    {/*<img src={logo} className="App-logo" alt="logo" />*/}

                        {/*<Button.Group>*/}
                            {/*<Button >后退</Button>*/}
                            {/*<Button >前进</Button>*/}
                        {/*</Button.Group>*/}

                {/*</header>*/}
                </HashRouter>
            </div>
        );
    }



}


export default App;


class Head extends Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
            username: "",
            password: "",
            visible: false,
            name:"登陆",
            loading: false,
        }
    }
    //模块选择
    handleChange = (msg) =>{
        this.props.changeMode(msg);
        console.log(`selected ${msg}`);
    }
    //登陆身份
    loginRole = (msg) => {
        this.props.changeMode(msg);

    }
    //弹出登陆框
    showDrawer = () => {
        this.setState({
            visible: true,
        });
    }
    //关闭登陆框
    onClose = () => {
        this.setState({
            visible: false,
        });
    };
    //修改用户名显示
    nameChange=(e)=>{
        this.setState({ name : e })
    }
    //username被修改
    usernameChange=(e)=>{
        this.setState({ username : e.target.value })
    }
    //password被修改
    passwordChange=(e)=>{
        this.setState({ password : e.target.value })
    }
    //登陆
    enterLoading = () => {
        this.setState({ loading: true });
        this.sendAjax();
        this.overLoading();
    }
    //点击登陆后旋转2秒
    overLoading = () => {
        setInterval(() => {this.setState({ loading: false })}, 2000);
    }
    //发送登陆请求
    sendAjax = () =>{
        //POST方式,IP为本机IP
        const username=this.state.username;//this.state.username;
        const password=this.state.password;//this.state.password;
        if(username===""||password===""){
            message.warning("用户名或密码不能为空！");
        }else{
            fetch("http://39.106.56.132:8080/userinfo/tologin", {

                method: "POST",
                //type:"post",
                //url:"http://39.106.56.132:8080/userinfo/tologin",
                mode: "cors",
                headers: {
                    "Content-Type": "application/json;charset=utf-8",
                },
                body: JSON.stringify({username:username,password:password}),
            }).then(function (res) {//function (res) {} 和 res => {}效果一致
                return res.json()
            }).then(json => {
                // get result
                const data = json;
                console.log(data);
                if(data.message==="登陆成功"){
                    this.nameChange(data.data.name);
                    message.success(data.message);
                    this.onClose();
                }else if(data.message==="账号密码有误"){
                    message.error("用户名或密码错误！");
                }else {
                    message.error("未知错误");
                }

            }).catch(function (e) {
                console.log("fetch fail");
                alert('系统错误');
            });

        }

    }
    //主函数
    render() {
        return (
            <div className={'head'}>
                {/*right*/}
                {/*<div>{this.props.name}</div>*/}
                <img src={logo} className="App-logo logo" alt="logo" />
                <span className={'companyName'}><h2><Link to='/welcome'>我的主页</Link></h2></span>
                {/*left*/}
                {/*<Input className={'searchText'} suffix={(*/}
                {/*<Button className="search-btn"  type="primary">*/}
                {/*<Icon type="search" />*/}
                {/*</Button>*/}
                {/*)}*/}
                {/*/>*/}
                <Select className={'headBtn1'} defaultValue="游客模式" style={{ width: 120 }} onChange={this.handleChange}>
                    <Select.Option value="游客模式">游客模式</Select.Option>
                    <Select.Option value="管理员模式">管理员模式</Select.Option>
                    <Select.Option value="用户模式">用户模式</Select.Option>
                </Select>
                <Button className={'headBtn1'} type="primary" onClick={this.showDrawer}>{this.state.name}</Button>
                <Button className={'headBtn1'} type='primary' onClick={this.loginRole}><Icon type="ellipsis" /></Button>
                <Input className={'searchText'} suffix={<Icon type="search"  />} />

                <Drawer title="用户登录" placement="right" onClose={this.onClose} visible={this.state.visible}>
                    <p>用户</p>
                    <Input type='' placeholder='用户名' onKeyUp={this.usernameChange}></Input>
                    <br/>
                    <br/>
                    <p>密码</p>
                    <Input type='password' placeholder='密码' onKeyUp={this.passwordChange}></Input>
                    <Button className={'headBtn1'} type='default' onClick={this.onClose}>忘记密码</Button>
                    <Button className={'headBtn2'} type='primary' loading={this.state.loading} onClick={this.enterLoading} >登陆</Button>
                    <Button className={'headBtn3'} type='default' onClick={this.sendAjax}>还没有账号？点击注册</Button>
                </Drawer>

            </div>
        );
    }
}
