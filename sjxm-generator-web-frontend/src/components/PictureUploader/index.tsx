import {
  uploadFileUsingPost,
} from '@/services/backend/fileController';
import {InboxOutlined, LoadingOutlined, PlusOutlined} from '@ant-design/icons';
// @ts-ignore
import {Card, Divider, Flex, message, Upload, UploadFile, UploadProps} from 'antd';
import {saveAs} from 'file-saver';
import React, {useState} from 'react';
import {COS_HOST} from "@/constants";


interface Props {
  biz: string;
  onChange?: (url: string) => void;
  value?: string;
}

/**
 * 文件上传组件
 * @constructor
 */
const PictureUploader: React.FC<Props> = (props) => {

  const {biz, onChange, value} = props

  const[loading,setLoading] = useState<boolean>(false)
  const uploadProps: UploadProps = {
    name: 'file',
    multiple: false,
    listType: "picture-card",
    showUploadList:false,
    maxCount: 1,
    disabled:loading,
    customRequest: async (fileObj: any) => {
      setLoading(true)
      try {
        const res = await uploadFileUsingPost({biz}, {}, fileObj.file);
        //拼接完整图片路径
        const fullPath = COS_HOST+res.data;
        onChange?.(fullPath ?? '');
        fileObj.onSuccess(res.data);
      } catch (e: any) {
        message.error('上传失败，' + e.message);
        fileObj.onError(e);
      }
      setLoading(false)
    },
  };

  const uploadButton = (
    <button style={{border:0,background:'none'}} type="button">
      {loading?<LoadingOutlined /> :<PlusOutlined/>}
      <div style={{marginTop:8}}>上传</div>
    </button>
  )

  return (
    <Upload {...uploadProps}>
      {value ? <img src={value} alt="picture" style={{width:'100%'}}/> : uploadButton }
    </Upload>
  );
};

export default PictureUploader;
