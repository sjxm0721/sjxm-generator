import {COS_HOST} from '@/constants';
import {
  downloadGeneratorByIdUsingGet,
  getGeneratorVoByIdUsingGet,
} from '@/services/backend/generatorController';
import {useParams} from '@@/exports';
import {PageContainer} from '@ant-design/pro-components';
import {useModel} from '@umijs/max';
import {Button, Card, Col, Image, message, Row, Space, Tabs, Tag, Typography, UploadFile} from 'antd';
import React, {useEffect, useState} from 'react';
import moment from "moment";
import {DownloadOutlined, EditOutlined} from "@ant-design/icons";
import FileConfig from "@/pages/Generator/Detail/components/FileConfig";
import ModelConfig from "@/pages/Generator/Detail/components/ModelConfig";
import AuthorInfo from "@/pages/Generator/Detail/components/AuthorInfo";
import {saveAs} from "file-saver";
import {Link} from "umi";

/**
 * 生成器详情页
 * @constructor
 */
const GeneratorDetailPage: React.FC = () => {
  const {id} = useParams();
  const [loading, setLoaing] = useState<boolean>(true)
  const [data, setData] = useState<API.GeneratorVO>({});
  const {initialState} = useModel('@@initialState');
  const {currentUser} = initialState ?? {};
  const my = data?.userId === currentUser?.id;

  /**
   * 加载数据
   */
  const loadData = async () => {
    if (!id) {
      return;
    }
    setLoaing(true)
    try {
      const res = await getGeneratorVoByIdUsingGet({
        id,
      });
      setData(res.data);
    } catch (error: any) {
      message.error('加载数据失败，' + error.message);
    }
    setLoaing(false)
  };

  useEffect(() => {
    if (id) {
      loadData();
    }
  }, [id]);


  /**
   * 标签列表
   * @param tags
   */
  const tagListView = (tags?: string[]) => {
    if (!tags) {
      return <></>;
    }

    return (
      <div style={{marginBottom: 8}}>
        {tags.map((tag) => (
          <Tag key={tag}>{tag}</Tag>
        ))}
      </div>
    );
  };


  const downloadButton = data.distPath && currentUser && (
    <Button icon={<DownloadOutlined/>} onClick={async () => {
      const blob = await downloadGeneratorByIdUsingGet(
        {
          id
        },
        {
          responseType: 'blob',
        },
      );
      // 使用 file-saver 来保存文件
      const fullPath = COS_HOST + data.distPath;
      saveAs(blob, fullPath.substring(fullPath.lastIndexOf('/') + 1));
    }}>下载</Button>
  )

  const editButton = my && (
    <Link to={`/generator/update?id=${data.id}`}>
      <Button icon={<EditOutlined/>}>编辑</Button>
    </Link>
  )


  return (
    <PageContainer title={<></>} loading={loading}>
      <Card>
        <Row justify="space-between" gutter={[24, 24]}>
          <Col flex="auto">
            <Space size="large" align="center">
              <Typography.Title level={4}>{data.name}</Typography.Title>
              {tagListView(data.tags)}
            </Space>
            <Typography.Paragraph>{data.description}</Typography.Paragraph>
            <Typography.Paragraph
              type="secondary">创建时间: {moment(data.createTime).format('YYYY-MM-DD hh:mm:ss')}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">基础包: {data.basePackage}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">版本: {data.version}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">作者: {data.author}</Typography.Paragraph>
            <div style={{marginBottom: 24}}/>
            <Space size="middle">
              <Link to={`/generator/use/${data.id}`}>
                <Button type="primary">立即使用</Button>
              </Link>
              {downloadButton}
              {editButton}
            </Space>
          </Col>
          <Col flex="320px">
            <Image src={data.picture}/>
          </Col>
        </Row>
      </Card>
      <div style={{marginBottom: 24}}></div>
      <Card>
        <Tabs defaultActiveKey={'fileConfig'} size="large" onChange={() => {
        }} items={[
          {
            key: 'fileConfig',
            label: '文件配置',
            children: <FileConfig data={data}/>,
          },
          {
            key: 'modelConfig',
            label: '模型配置',
            children: <ModelConfig data={data}/>,
          },
          {
            key: 'userInfo',
            label: '作者信息',
            children: <AuthorInfo data={data}/>,
          },
        ]}/>

      </Card>
    </PageContainer>
  );
};

export default GeneratorDetailPage;
