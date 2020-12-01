export interface Feature {
  featureId: number;
  featureName: string;
  featureNameEn: string;
  description: string;
  dateIns: Date;
  featureStatus?: {
    name: string;
    value: number;
  };
  author?: {
    name: string;
    value: number;
  };
  responsible?: {
    name: string;
    value: number;
  };
}

export interface FeatureCreate {
  featureName: string;
  featureNameEn: string;
  description?: string;
}

export interface FeatureUpdate {
  featureName: string;
  featureNameEn: string;
  description?: string;
  responsibleId?: number;
}

export interface FeatureSearchTemplate {
  featureId?: number;
  featureNameTemplate?: string;
  featureNameEnTemplate?: string;
  dateInsFrom?: Date;
  dateInsTo?: Date;
  statusCodeList?: string[];
}
