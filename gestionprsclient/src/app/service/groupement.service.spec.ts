import { TestBed } from '@angular/core/testing';

import { GroupementService } from './groupement.service';

describe('GroupementService', () => {
  let service: GroupementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
