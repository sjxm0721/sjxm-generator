import {
  uploadFileUsingPost,
} from '@/services/backend/fileController';
import {InboxOutlined} from '@ant-design/icons';
// @ts-ignore
import {Card, Divider, Flex, message, Upload, UploadFile, UploadProps} from 'antd';
import {saveAs} from 'file-saver';
import React, {useState} from 'react';

const {Dragger} = Upload;

interface Props {
  biz: string;
  onChange?: (fileList: UploadFile[]) => void;
  value?: UploadFile[];
  description?: string;
}

/**
 * 文件上传组件
 * @constructor
 */
const FileUploader: React.FC<Props> = (props) => {

  const {biz, onChange, value, description} = props

  const[loading,setLoading] = useState<boolean>(false)
  const uploadProps: UploadProps = {
    name: 'file',
    multiple: false,
    listType: "text",
    maxCount: 1,
    fileList: value,
    disabled:loading,
    onChange({fileList}) {
      onChange?.(fileList);
    },
    customRequest: async (fileObj: any) => {
      setLoading(true)
      try {
        const res = await uploadFileUsingPost({biz}, {}, fileObj.file);
        fileObj.onSuccess(res.data);
      } catch (e: any) {
        message.error('上传失败，' + e.message);
        fileObj.onError(e);
      }
      setLoading(false)
    },
  };

  return (
    <Dragger {...uploadProps}>
      <p className="ant-upload-drag-icon">
        <InboxOutlined/>
      </p>
      <p className="ant-upload-text">点击或拖拽文件上传</p>
      <p className="ant-upload-hint">
        {description}
      </p>
    </Dragger>
  );
};

export default FileUploader;
