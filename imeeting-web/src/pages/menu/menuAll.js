import React, { Component } from 'react';
import {Table,Input,Button,Icon} from 'antd';
import {Link} from "react-router-dom";
import "@/css/menu.css";
import img1 from "@/logo.svg";
//表格

//表格内容
const data = [];
//表格内容可以用for循环添加
for ( let i=5;i<1300;i++){
    data.push({
        key: i,
        img:<img src={img1} className="foodImg" alt={i}/>,
        name: i+"菜",
        price: i+10+"元",
        info:"加信息"+i+"资料资料"+i+"OKOK"+i+"资料信息资料信息",
        add: <Button >加入购物车</Button>,
    });
}

// rowSelection object indicates the need for row selection
const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
    getCheckboxProps: record => ({
        disabled: record.name === 'Disabled User', // Column configuration not to be checked
        name: record.name,
    }),
};

class menuAll extends Component {
    state = {
        searchText: '',
    };

    handleSearch = (selectedKeys, confirm) => () => {
        confirm();
        this.setState({ searchText: selectedKeys[0] });
    }

    handleReset = clearFilters => () => {
        clearFilters();
        this.setState({ searchText: '' });
    }
    render() {
        //表格条目
        const columns = [
            {
                title:'图片',
                dataIndex:'img',
            }, {
                title: '菜品',
                dataIndex: 'name',
                filterDropdown: ({ setSelectedKeys, selectedKeys, confirm, clearFilters }) => (
                    <div className="custom-filter-dropdown">
                        <Input
                            ref={ele => this.searchInput = ele}
                            placeholder="Search name"
                            value={selectedKeys[0]}
                            onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                            onPressEnter={this.handleSearch(selectedKeys, confirm)}
                        />
                        <Button type="primary" onClick={this.handleSearch(selectedKeys, confirm)}>Search</Button>
                        <Button onClick={this.handleReset(clearFilters)}>Reset</Button>
                    </div>
                ),
                filterIcon: filtered => <Icon type="search-o" style={{ color: filtered ? '#108ee9' : '#aaa' }} />,
                onFilter: (value, record) => record.name.toLowerCase().includes(value.toLowerCase()),
                onFilterDropdownVisibleChange: (visible) => {
                    if (visible) {
                        setTimeout(() => {
                            this.searchInput.focus();
                        });
                    }
                },
                render: (text) => {
                    const { searchText } = this.state;
                    return searchText ? (
                        <span>
            {text.split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i')).map((fragment, i) => (
                fragment.toLowerCase() === searchText.toLowerCase()
                    ? <span key={i} className="highlight">{fragment}</span> : fragment // eslint-disable-line
            ))}
          </span>
                    ) : text;
                },
            }, {
                title: '简介',
                dataIndex: 'info',
            }, {
                title: '单价',
                dataIndex: 'price',
            }, {
                title: '选择',
                dataIndex: 'add',
            }];
        return (
            <div >
                <br/>
                <br/>
                <h1><Link to={'../welcome'}>所有菜单</Link></h1>
                <Button type="primary" >全部加入购物车</Button>
                <Table rowSelection={rowSelection} columns={columns} dataSource={data} />
            </div>
        );
    }
}

export default menuAll;
